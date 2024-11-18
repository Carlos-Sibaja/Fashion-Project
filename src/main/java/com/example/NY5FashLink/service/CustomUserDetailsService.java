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

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(email);

        if (users == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(users.getEmail())
                .password(users.getPassword())
                .roles(users.getRole().toString())
                .build();
    }
}
