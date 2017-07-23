package com.ums.umsbackend.services;

import com.ums.umsbackend.domains.Users;

/**
 * Created by Naveen on 16/07/2017.
 */
public interface UserService {

        void save(Users user);

        Users findByUsername(String username);
}
