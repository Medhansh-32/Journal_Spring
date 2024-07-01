package com.edigest.journalApp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.UserService;


@RestController
@RequestMapping("/user")
public class UserContoller {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
       return userService.getAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
           User temp= userService.saveUser(user);
           return new ResponseEntity<>(temp,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String userName){
        User oldUser=userService.findByUserName(userName);
        if(oldUser!=null){
            oldUser.setUserName(user.getUserName());
            oldUser.setPassword(user.getPassword());
            userService.saveUser(oldUser);
            return new ResponseEntity<>(oldUser,HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
     @GetMapping("/{userName}")
     public ResponseEntity<User> findByUsername(@PathVariable String userName){
        User user=userService.findByUserName(userName);
        if(user!=null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}



