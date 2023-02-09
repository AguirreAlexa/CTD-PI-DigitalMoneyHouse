package dh.pi.userservice.service;

import dh.pi.userservice.entity.Account;
import dh.pi.userservice.entity.User;
import dh.pi.userservice.entity.dto.UserRegisterDTO;
import dh.pi.userservice.entity.utils.UserCvuAlias;
import dh.pi.userservice.exception.AuthenticationException;
import dh.pi.userservice.exception.BadRequestException;
import dh.pi.userservice.exception.ResourceNotFoundException;
import dh.pi.userservice.keycloak.KeyCloakService;
import dh.pi.userservice.repository.UserRepository;
import dh.pi.userservice.repository.feign.IAccountFeignRepository;
import dh.pi.userservice.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    UserRepository userRepository;
    KeyCloakService keyCloakService;
    IAccountFeignRepository accountFeignRepository;

    public UserService(UserRepository userRepository, KeyCloakService keyCloakService, IAccountFeignRepository accountFeignRepository) {
        this.userRepository = userRepository;
        this.keyCloakService = keyCloakService;
        this.accountFeignRepository = accountFeignRepository;
    }

    public List<User> getAll() {
        keyCloakService.getRealm().users().list();
        return userRepository.findAll();
    }

    @Override
    public Optional<UserRegisterDTO> save(User user) throws Exception {
        Optional<User> userExists = userRepository.findByEmail(user.getEmail());

        if(userExists.isEmpty()){
            if(user.getFirstName() == null || user.getFirstName().isEmpty()){
                throw new BadRequestException("Falta completar el campo nombre");
            }
            if(user.getLastName() == null || user.getLastName().isEmpty()){
                throw new BadRequestException("Falta completar el campo apellido");
            }
            if(user.getDni() == null || user.getDni().isEmpty()){
                throw new BadRequestException("Falta completar el campo dni");
            }
            if(user.getEmail() == null || user.getEmail().isEmpty()){
                throw new BadRequestException("Falta completar el campo email");
            }
            if(user.getPhoneNumber() == null ||  user.getPhoneNumber().isEmpty()){
                throw new BadRequestException("Falta completar el campo telefono");
            }
            if(user.getUsername() == null || user.getUsername().isEmpty()){
                throw new BadRequestException("Falta completar el campo nombre de usuario");
            }
            if(user.getPassword() == null || user.getPassword().isEmpty()){
                throw new BadRequestException("Falta completar el campo contraseña");
            }

            user.setAlias(User.armarAlias());
            user.setCvu(User.cadenaAleatoria(22));

            User userSaved = keyCloakService.createUser(user);
            userSaved.setPassword(user.getPassword());
            User userDB = userRepository.save(userSaved);

            Account account = accountFeignRepository.createAccount(userDB.getId()).getBody();
            Optional<UserRegisterDTO> userRegisterDTO = convertUserToUserRegisterDTO(userDB, account);

            return userRegisterDTO;
        }else{
            throw new BadRequestException("Ya existe un usario con ese nombre de usuario");
        }
    }

    @Override
    public User update(Integer userId, User user) throws Exception {
        Optional<User> userExists = userRepository.findById(userId);
        User userSaved = userExists.get();

        if(userExists.isPresent()){
            if (user.getUsername() != null && userSaved.getUsername() != user.getUsername() && !user.getUsername().isEmpty()) {
                userSaved.setUsername(user.getUsername());
            }
            if (user.getFirstName() != null && user.getFirstName() != userSaved.getFirstName() && !user.getFirstName().isEmpty()) {
                userSaved.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null && userSaved.getLastName() != user.getLastName() && !user.getLastName().isEmpty()) {
                userSaved.setLastName(user.getLastName());
            }
            if (user.getEmail() != null && userSaved.getEmail() != user.getEmail()) {
                userSaved.setEmail(user.getEmail());
            }
            if (user.getPassword() != null && userSaved.getPassword() != user.getPassword() && !user.getPassword().isEmpty()) {
                userSaved.setPassword(user.getPassword());
            }
            if (user.getPhoneNumber() != null && userSaved.getPhoneNumber() != user.getPhoneNumber() && !user.getPhoneNumber().isEmpty()){
                userSaved.setPhoneNumber(user.getPhoneNumber());
            }
            if (user.getDni() != null && userSaved.getDni() != user.getDni() && !user.getDni().isEmpty()) {
                userSaved.setDni(user.getDni());
            }
            if (user.getCvu() != null){
                throw new BadRequestException("No se puede modificar el cvu");
            }

            keyCloakService.updateDataUser(userExists.get(), user);
            userRepository.save(userSaved);

            return userSaved;
        }else{
            throw new ResourceNotFoundException("No existe un usario con ese id");
        }
    }

    @Override
    public String login(String email, String password) throws Exception{
        Optional<User> userExists = userRepository.findByEmail(email);

        if(userExists.isPresent()){
            return keyCloakService.login(email, password);

        } else {
            throw new BadRequestException("No existe usuario con este email");
        }

    }

    @Override
    public void logout(String userId) {
        keyCloakService.logout(userId);
    }

    @Override
    public Optional<UserRegisterDTO> findById(Integer id) throws ResourceNotFoundException{
        Optional<User> userExists = userRepository.findById(id);
        Account account = accountFeignRepository.getAccountDetailsByUserId(id).getBody();

        if(account == null){
            throw new ResourceNotFoundException("No existe account para este usuario");
        }
        if(userExists.isPresent()){
            //keyCloakService.getRealm().users().get(userExists.get().getKeycloakId()).credentials() ;
            Optional<UserRegisterDTO> userRegisterDTO = convertUserToUserRegisterDTO(userExists.get(),account);
            return userRegisterDTO;
        }else{
            throw new ResourceNotFoundException("No existe usuario con este id");
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("No se encontro usuario con este mail");
        }
        return user;
    }

    @Override
    public Optional<UserCvuAlias> getCvuAndAliasByUserId(Integer id) throws ResourceNotFoundException{
        User optionalUser = userRepository.findById(id).get();
        UserCvuAlias user = new UserCvuAlias(id, optionalUser.getCvu(), optionalUser.getAlias());

        if(user == null){
            throw new ResourceNotFoundException("No se encontro cvu y alias para este id");
        }
        return Optional.of(user);
    }


    // CONVERTER
    public Optional<UserRegisterDTO> convertUserToUserRegisterDTO(User user, Account account){
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(user.getId(),
                user.getFirstName(), user.getLastName(), user.getDni(),
                user.getEmail(), user.getPhoneNumber(), user.getUsername(),
                user.getCvu(), user.getAlias(), account);
        return Optional.of(userRegisterDTO);
    }
}
