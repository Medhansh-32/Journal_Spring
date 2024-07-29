package com.edigest.journalApp.controller;

import com.edigest.journalApp.apiresponse.WeatherResponse;
import com.edigest.journalApp.cache.AppCache;
import com.edigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private WeatherService weatherService;



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
    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse=weatherService.getWeather("Mumbai");

    if(weatherResponse !=null){
            String greeting="hi "+authentication.getName()+" Todays Weather is "+weatherResponse.getCurrent().getCondition().getText();
            return new ResponseEntity<>(greeting,HttpStatus.OK);
        }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }


}



