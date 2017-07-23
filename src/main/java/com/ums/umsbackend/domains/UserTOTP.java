package com.ums.umsbackend.domains;

import lombok.Data;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Naveen on 18/07/2017.
 */
@Entity
@Table(name = "user_totp")
public class UserTOTP {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

    private String code;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

//    @OneToOne(mappedBy = "userTOTP")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", unique = true)
    private Users user;

    protected UserTOTP(){

    }

    public UserTOTP(Long id, String code, LocalDateTime creationTime, Users user) {
        this.id = id;
        this.code = code;
        this.creationTime = creationTime;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
