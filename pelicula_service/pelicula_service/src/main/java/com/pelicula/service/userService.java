package com.pelicula.service;

import com.pelicula.enums.Role;
import com.pelicula.exception.InvalidRoleException;
import com.pelicula.model.User;
import com.pelicula.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class userService {

    @Autowired
    private UserRepository userRepository;



    public User createUser(User user) {


        String role = user.getRole().name();

        if (!Role.isValid(role)) {
            throw new InvalidRoleException("Invalid role: " + role + ". Allowed roles are: " + Role.getValidRoles());
        }

        if (user.getRegistrationDate() == null) {
            user.setRegistrationDate(new Date());
        }

        if (user.getLastAccess() == null) {
            user.setLastAccess(null);
        }
        user.setStatus(true);

        return userRepository.save(user);
    }


    // Metodo para actualizar usuario
    public User updateUser(String userId, User updatedUser) {
//        User updatedUser = new User();
//        updatedUser.setName(user.getName());
//        updatedUser.setEmail(user.getEmail());
//        updatedUser.setPassword(user.getPassword());
//        updatedUser.setRole(user.getRole());
//        updatedUser.setStatus(user.getStatus());


        Optional<User> existingUserOpt = userRepository.findById(userId);
        if (existingUserOpt.isEmpty()) {
            throw new RuntimeException("User with ID " + userId + " not found.");
        }

        User existingUser = existingUserOpt.get();

        // Actualizar solo los campos no nulos de updatedUser
        if (updatedUser.getName() != null) {
            existingUser.setName(updatedUser.getName());
        }

        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }

        if (updatedUser.getRole() != null) {
            String role = updatedUser.getRole().name();
            if (!Role.isValid(role)) {
                throw new InvalidRoleException("Invalid role: " + role + ". Allowed roles are: " + Role.getValidRoles());
            }
            existingUser.setRole(updatedUser.getRole());
        }

        if (updatedUser.getStatus() != null) {
            existingUser.setStatus(updatedUser.getStatus());
        }

        existingUser.setLastAccess(new Date());

        return userRepository.save(existingUser);
    }


    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with id: " + id)));
    }


    public void validateAndPerformAction(String role) {
        if (!Role.isValid(role)) {
            throw new InvalidRoleException("Invalid role: " + role);
        }

        Role validRole = Role.valueOf(role.toUpperCase());
        if (validRole == Role.ADMIN) {
            performAdminAction();
        } else {
            System.out.println("Action not available for role: " + validRole);
        }
    }

    private void performAdminAction() {
        System.out.println("Admin action executed.");
    }

}
