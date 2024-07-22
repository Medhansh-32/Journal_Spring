package com.edigest.journalApp.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok";
    }
    @Autowired
    private UserService userService;

   
    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user){

         boolean res=  userService.saveEntry(user);
        if(res) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
