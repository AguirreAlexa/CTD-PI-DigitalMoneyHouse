package dh.pi.userservice.service.interfaces;

import dh.pi.userservice.entity.*;
import dh.pi.userservice.entity.dto.UserRegisterDTO;
import dh.pi.userservice.entity.utils.UserCvuAlias;
import dh.pi.userservice.exception.ResourceNotFoundException;

import java.util.Optional;

public interface IUserService {
    Optional<UserRegisterDTO> save(User user) throws Exception;
    User update(Integer userId, User user) throws Exception;
    String login(String email, String password) throws Exception;
    void logout(String userId) throws Exception;
    Optional<UserRegisterDTO> findById(Integer id) throws ResourceNotFoundException;
    Optional<User> findByEmail(String email) throws ResourceNotFoundException;
    Optional<UserCvuAlias> getCvuAndAliasByUserId(Integer id) throws ResourceNotFoundException;


}