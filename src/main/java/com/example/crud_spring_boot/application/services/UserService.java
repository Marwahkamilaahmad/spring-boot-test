package com.example.crud_spring_boot.application.services;

import com.example.crud_spring_boot.domain.models.AppUserRole;
import com.example.crud_spring_boot.domain.models.User;
import com.example.crud_spring_boot.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setRoles(Collections.singleton(AppUserRole.USER));
        userRepository.save(user);
    }

    public User findByUsername(String email) {
        return userRepository.findByUsername(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList()
        );

    }

}
