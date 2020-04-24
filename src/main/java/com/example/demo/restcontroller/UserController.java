package com.example.demo.restcontroller;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRequest userRequest;
    @Autowired
    private UserResponse userResponse;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Value( "${example}" )
    private String example;

    @GetMapping(
            value = "/users/name",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity findByLastName(@RequestParam String lastName){
        System.out.println("Lastname in controller"+lastName);
        if (null == lastName) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        List<UserResponse> userResponses = userService.findByLastName(lastName);
        if (userResponses.size()==0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(userResponses);
    }

    @GetMapping(
            value = "/users",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity fetchUsers(){
        log.debug(" Example is ======> "+example);
        List<UserResponse> users = userService.fetchUsers();
        // BeanUtils.copyProperties(users, userResponse);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(
            value = "/users/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
            )
    public ResponseEntity fetchUsersById(@PathVariable Long id){
        if (id < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        UserResponse userResponse = userService.fetchUsersById(id);
        if (userResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println("userResponse --->"+userResponse);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping(
            value = "/users",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
            )
    public ResponseEntity addUser(@Valid@RequestBody UserRequest userRequest) throws URISyntaxException {
        log.debug("Inside Add User Method");
        User user = modelMapper.map(userRequest,User.class);
        UserResponse userResponse = userService.addUser(user);
        return ResponseEntity.created(new URI(String.valueOf(userResponse.getId()))).body(userResponse);
    }

    @PutMapping(
            value = "/users/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) throws URISyntaxException {
        User user = modelMapper.map(userRequest,User.class);
        UserResponse userResponse = userService.updateUser(user);
        System.out.println("userResponse updateUser --->"+userResponse);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping(
            value = "/users/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(id);
    }

}
