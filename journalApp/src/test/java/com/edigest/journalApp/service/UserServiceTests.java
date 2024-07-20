package com.edigest.journalApp.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.edigest.journalApp.repository.UserRepository;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;
    
    
    @Test
    public void testFindByUserName(){
       // userRepository.findByUserName("ram");
        assertNotNull(userRepository.findByUserName("Medhansh Sharma"));
    }
    @ParameterizedTest
    @CsvSource({
        "Keshav Sharma",
        "Medhansh Sharma",
        "Surjeet Sharma"
    })
    public void testUser(String userName){
     assertNotNull(userRepository.findByUserName(userName),"failed for username:"+userName);   
    }
}
