package com.ums.umsbackend.services;

import com.ums.umsbackend.domains.Users;
import com.ums.umsbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Naveen on 16/07/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void save(Users user) {

        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Users findByUsername(String username) {
        return null;
    }
}
