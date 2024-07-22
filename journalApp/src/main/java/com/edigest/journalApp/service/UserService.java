package com.edigest.journalApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepository;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


public boolean saveEntry(User user){
    try {
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        return true;
    }catch (Exception e){
        log.warn("Duplicate Username Entry is 'Invalid' ");
    }
return false;
}
public User saveAdmin(User user){
  user.setPassword(passwordEncoder.encode((user.getPassword())));
  user.setRoles(Arrays.asList("USER","ADMIN"));
  userRepository.save(user);
  return user;
}

    public User saveUser(User user){
     return userRepository.save(user);
   }

   
   public List<User> getAll(){
    return userRepository.findAll();
   }
    
   public Optional<User> findById(ObjectId id){
    return userRepository.findById(id);
   }

   public void deleteById(ObjectId id){
    userRepository.deleteById(id);
   }

   public User findByUserName(String userName){
    return userRepository.findByUserName(userName);

   }

}
