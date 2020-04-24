package com.example.demo.restcontroller;

import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public class UCBackup {
    /*@Autowired
    private User users;*/
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity fetchUsers(){
        // String users ="{\"id\":1, \"name\":\"A\",\"zip\":1}";
       /* users.setId(1);
        users.setName("Apple");
        users.setZip(84129);*/

        List<UserResponse> users = userService.fetchUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity fetchUsersById(@PathVariable Long id){
        // String users ="{\"id\":1, \"name\":\"A\",\"zip\":1}";
       /* users.setId(1);
        users.setName("Apple");
        users.setZip(84129);*/

        UserResponse userResponse = userService.fetchUsersById(id);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user) throws URISyntaxException {
        // String users ="{\"id\":1, \"name\":\"A\",\"zip\":1}";
      /*  users.setId(user.getId());
        users.setName(user.getName());
        users.setZip(user.getZip());
        return ResponseEntity.created(new URI(String.valueOf(users.getId()))).body(users);*/
        return ResponseEntity.created(new URI(String.valueOf(1))).body(1);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) throws URISyntaxException {
        // String users ="{\"id\":1, \"name\":\"A\",\"zip\":1}";
       /* users.setName(user.getName());
        users.setZip(user.getZip());
        return ResponseEntity.ok(id);*/
        return ResponseEntity.ok(id);
    }

  /*  @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) throws URISyntaxException {
        // String users ="{\"id\":1, \"name\":\"A\",\"zip\":1}";
        return ResponseEntity.ok(id);
    }*/
}
