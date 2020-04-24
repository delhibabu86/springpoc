package com.example.demo.service;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<UserResponse> fetchUsers(){//Pageable paging = null;
        Iterable<User>  users= userRepository.findAll();
        // Create Conversion Type
        Type listType = new TypeToken<List<UserResponse>>() {}.getType();
        // Convert List of Entity objects to a List of DTOs objects
        List<UserResponse> returnValue = modelMapper.map(users, listType);
        return returnValue;

       /* ModelMapper modelMapper = new ModelMapper();
        return (List<UserResponse>)modelMapper.map(users,UserResponse.class);*/
    }

    public UserResponse fetchUsersById(Long id){
        Optional<User> user = userRepository.findById(id);
       UserResponse userResponse = modelMapper.map(user,UserResponse.class);
       // System.out.println("userResponse service--->"+userResponse);
       return userResponse;
    }

    @Transactional
    public UserResponse addUser(User user){//Pageable paging = null;
       User users = userRepository.save(user);
        UserResponse userResponse = modelMapper.map(users,UserResponse.class);
        return userResponse;
    }
    @Transactional
    public UserResponse updateUser(User user){
        System.out.println("User updateUser service--->"+user);
        User users = userRepository.save(user);
        System.out.println("users updateUser service--->"+users);
        UserResponse userResponse = modelMapper.map(users,UserResponse.class);
        System.out.println("userResponse updateUser service--->"+userResponse.toString());
        return userResponse;
    }
    @Transactional
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public List<UserResponse> findByLastName(String lastName){
        System.out.println("Last Name is ===>"+lastName);
        List<User> users = userRepository.findByLastName(lastName);
        for(User user : users){
            System.out.println(user);
        }
        // Create Conversion Type
        Type listType = new TypeToken<List<UserResponse>>() {}.getType();
        // Convert List of Entity objects to a List of DTOs objects
        List<UserResponse> returnValue = modelMapper.map(users, listType);
        return returnValue;
    }
}
