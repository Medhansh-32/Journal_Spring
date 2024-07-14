package com.edigest.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepository;
import com.edigest.journalApp.service.UserService;

 
@RestController
@RequestMapping("/user")
public class UserContoller {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User oldUser=userService.findByUserName(userName);
   
            oldUser.setUserName(user.getUserName());
            oldUser.setPassword(user.getPassword());
            userService.saveEntry(oldUser);
            return new ResponseEntity<>(oldUser,HttpStatus.NO_CONTENT);
        }
    
    @DeleteMapping
    public ResponseEntity<?> deleteByUsername(){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userRepository.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



