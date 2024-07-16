package com.edigest.journalApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepository;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

public User saveEntry(User user){
  user.setPassword(passwordEncoder.encode((user.getPassword())));
  user.setRoles(Arrays.asList("USER"));
  userRepository.save(user);
  return user;
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
