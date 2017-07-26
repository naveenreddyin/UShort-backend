package com.ums.umsbackend.services;

import com.ums.umsbackend.domains.Role;
import com.ums.umsbackend.domains.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by Naveen on 26/07/2017.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private Users user;

    public CurrentUser(Users user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public Users getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public Role getRole() {
        return user.getRole();
    }
}
