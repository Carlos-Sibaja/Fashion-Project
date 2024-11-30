package com.example.NY5FashLink.service;

import com.example.NY5FashLink.model.Role;
import com.example.NY5FashLink.model.Users;
import com.example.NY5FashLink.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        customUserDetailsService = new CustomUserDetailsService(userRepository);
    }

    @Test
    void loadUserByUsername_ShouldReturnUserDetails_WhenUserExists() {

        String email = "test@gmail.com";
        Users user = new Users();
        user.setEmail(email);
        user.setPassword("$2a$10$5bO2ZtUZM2dR5W9j0R3J0.1mshsi./aRfcbffEIFu9iM0RgNkGvyq");
        user.setRole(Role.CUSTOMER);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());
        assertEquals("$2a$10$5bO2ZtUZM2dR5W9j0R3J0.1mshsi./aRfcbffEIFu9iM0RgNkGvyq", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_CUSTOMER")));

        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void loadUserByUsername_ShouldThrowException_WhenUserDoesNotExist() {

        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());


        assertThrows(UsernameNotFoundException.class,
                () -> customUserDetailsService.loadUserByUsername(email));

        verify(userRepository, times(1)).findByEmail(email);
    }
}