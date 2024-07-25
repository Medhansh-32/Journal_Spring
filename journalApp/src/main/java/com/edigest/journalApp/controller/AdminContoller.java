package com.edigest.journalApp.controller;

import com.edigest.journalApp.cache.AppCache;
import com.edigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.edigest.journalApp.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminContoller {
    
    @Autowired
    private UserService userService;
    @Autowired
    private AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
    List<User> all=userService.getAll();
    if(!all.isEmpty() && all !=null){
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);   
}
@PostMapping("/create-new-user")
public ResponseEntity<?> createUser(@RequestBody User user){
    try {
        userService.saveAdmin(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
    @GetMapping("/clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }

}
