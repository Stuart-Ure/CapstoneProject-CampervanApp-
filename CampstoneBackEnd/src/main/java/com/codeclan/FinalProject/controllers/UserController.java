package com.codeclan.FinalProject.controllers;

import com.codeclan.FinalProject.models.User;
import com.codeclan.FinalProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.RowSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

//    Create a user
@PostMapping(value = "/users")
public ResponseEntity<User> createUser(@RequestBody User user) {
     userRepository.save(user);
     return new ResponseEntity<>(user,HttpStatus.OK);
}

    @GetMapping(value= "/users/{id}")
    public ResponseEntity  getUser(@PathVariable Long id){
        return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
    }
    @PutMapping(value = "/users/{id}")
    public User replaceUser(@PathVariable Long id, @RequestBody User newUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setProfilePicture(newUser.getProfilePicture());

            return userRepository.save(user);
        }).orElseGet(() -> {
            newUser.setUserId(id);
            return userRepository.save(newUser);
        });
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    };

}
