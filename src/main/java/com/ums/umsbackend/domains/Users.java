package com.ums.umsbackend.domains;

import javax.persistence.*;

import lombok.Data;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

/**
 * Created by Naveen on 16/07/2017.
 */
@Entity
@Table(name = "users")
public class Users {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    @Column(name = "username", unique = true)
    private String email;
    private String password;
    private boolean enabled = true;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToOne(mappedBy = "user")
    private UserTOTP userTOTP;

    public UserTOTP getUserTOTP() {
        return userTOTP;
    }

    public void setUserTOTP(UserTOTP userTOTP) {
        this.userTOTP = userTOTP;
    }

    protected Users(){

    }

    public Users(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }
}

