package com.edigest.journalApp.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.UserRepository;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadByUserNameTest() {
        // Arrange: Set up the mock behavior to return an instance of your User entity
        User mockUser = new User();
        mockUser.setUserName("Medhansh Sharma");
        mockUser.setPassword("12345678");
        List<String> mockRole=new ArrayList<>();
        mockRole.add("ADMIN");
        mockUser.setRoles(mockRole);
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(mockUser);

        // Act: Call the method to be tested
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("Medhansh Sharma");

        // Assert: Verify the behavior
        assertNotNull(userDetails);
        assertEquals("Medhansh Sharma", userDetails.getUsername());
    }
}
