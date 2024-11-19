package com.example.NY5FashLink.service;

import com.example.NY5FashLink.model.Users;
import com.example.NY5FashLink.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    // Handles log in info authentication
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> users = userRepository.findByEmail(email);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(users.get().getEmail())
                .password(users.get().getPassword())
                .roles(users.get().getRole().toString())
                .build();
    }
}
