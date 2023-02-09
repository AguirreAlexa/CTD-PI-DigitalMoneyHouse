package dh.pi.userservice.controller;

import dh.pi.userservice.entity.User;
import dh.pi.userservice.entity.dto.UserRegisterDTO;
import dh.pi.userservice.entity.dto.UserUpdateDTO;
import dh.pi.userservice.entity.utils.Login;
import dh.pi.userservice.entity.utils.UserCvuAlias;
import dh.pi.userservice.exception.BadRequestException;
import dh.pi.userservice.exception.ResourceNotFoundException;
import dh.pi.userservice.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "Returns all users that respond to the filter", response = User[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 403, message = "Access forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns all users that respond to the filter", response = User[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 403, message = "Access forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<UserRegisterDTO> getById(@PathVariable Integer id) throws ResourceNotFoundException{
        Optional<UserRegisterDTO> foundUser = userService.findById(id);
        if(foundUser.isPresent()){
            return ResponseEntity.ok(foundUser.get());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Returns response after creating a new user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 403, message = "Access forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> register(@Valid @RequestBody User user) throws Exception {
        Optional<UserRegisterDTO> savedUser = userService.save(user);

        if(savedUser.isPresent()){
            return ResponseEntity.ok(savedUser);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Returns response after updating an existing user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 403, message = "Access forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<User> patch(@PathVariable Integer id, UserUpdateDTO userDTO) throws Exception{
        //String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        //Optional<User> foundUser = userService.findById(id);
        User user = userService.update(id, userDTO);
        if(user != null){
            return ResponseEntity.ok(user);
        }else{
            throw new BadRequestException("Error al procesar el metodo");
        }
    }

    @PostMapping("/login")
    @ApiOperation(value = "Returns response after login with a user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 403, message = "Access forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> login(@RequestBody Login login) throws Exception {
        String credentials = userService.login(login.getEmail(), login.getPassword());

        if (credentials != null) {
            return ResponseEntity.ok(credentials);
        } else if (userService.findByEmail(login.getEmail()).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value="/logout")
    @ApiOperation(value = "Returns response after logout", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 403, message = "Access forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<?> logoutPage () throws Exception {

        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userId);

        if (userId.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        userService.logout(userId);
        return ResponseEntity.ok("User logout successful");
    }

    @GetMapping("/{id}/getCvuAlias")
    @ApiOperation(value = "Returns response after getting cvu and alias", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 403, message = "Access forbidden"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Optional<UserCvuAlias>> getCvuAndAliasByUserId(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)throws ResourceNotFoundException {
        Optional<UserCvuAlias> user = userService.getCvuAndAliasByUserId(id);
        System.out.println(userService.getCvuAndAliasByUserId(id));

        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // CONVERTER

}

