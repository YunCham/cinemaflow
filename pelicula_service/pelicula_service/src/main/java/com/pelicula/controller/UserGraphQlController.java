package com.pelicula.controller;

import com.pelicula.dto.UserRequest;
import com.pelicula.model.User;
import com.pelicula.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class UserGraphQlController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private com.pelicula.service.userService userService;

    @QueryMapping
    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .peek(user -> user.setPassword(null))
                .collect(Collectors.toList());
    }

    @QueryMapping
    public User getUserById(@Argument String userid) {
        return userService.getUserById(userid);
    }

    @MutationMapping
    public User createUser(@Argument User user) {

        user.setId(UUID.randomUUID().toString());
        return userService.createUser(user);
    }


    @MutationMapping
    public User updateUser(@Argument String userId, @Argument UserRequest userRequest) {
        User updatedUser = new User();
        updatedUser.setName(userRequest.name());
        updatedUser.setEmail(userRequest.email());
        updatedUser.setPassword(userRequest.password());
        updatedUser.setRole(userRequest.role());
        updatedUser.setStatus(userRequest.status());

        return userService.updateUser(userId, updatedUser);
    }



}
