package com.ums.umsbackend.repositories;

import static org.assertj.core.api.Assertions.*;

import com.ums.umsbackend.domains.Duration;
import com.ums.umsbackend.domains.UserTOTP;
import com.ums.umsbackend.domains.Users;
import com.ums.umsbackend.repositories.TOTPRepository;
import com.ums.umsbackend.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * Created by Naveen on 18/07/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TOTPRepositoryTests  {

    @Autowired
    private TOTPRepository totpRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DurationConfigRespository durationConfigRespository;

    private Users user;
    private UserTOTP userTOTP;

    @Before
    public void setUp(){
        user = new Users(1L,"naveen@dispostable.com", "123456");
        Users userSaveResponse = userRepository.save(user);

        final LocalDateTime today = LocalDateTime.now();
        userTOTP = new UserTOTP(1L, "0F0F0F", today, userSaveResponse);
        totpRepository.save(userTOTP);
    }

    @Test
    public void testToSaveCodeForUser(){

        UserTOTP userTOTPSaved = totpRepository.save(userTOTP);

        assertThat(userTOTPSaved.getCode()).isNotNull();

    }

    @Test
    public void testDeleteForUser(){
        totpRepository.deleteAll();
        assertThat(totpRepository.count()).isEqualTo(0);
        UserTOTP userSaveResponse = totpRepository.save(userTOTP);
        assertThat(totpRepository.count()).isEqualTo(1);

        totpRepository.delete(userSaveResponse.getId());
        assertThat(totpRepository.count()).isEqualTo(0);

        totpRepository.save(userTOTP);
        assertThat(totpRepository.count()).isEqualTo(1);
        // now let us try to delete user by email
        totpRepository.deleteUserByUserEmail("naveen@dispostable.com");
        assertThat(totpRepository.count()).isEqualTo(0);

    }

    @Test
    public void testToGetByUserEmail(){
        UserTOTP userTOTP = totpRepository.findUserByUserEmail("naveen@dispostable.com");
        assertThat(userTOTP.getUser().getEmail()).isEqualToIgnoringCase("naveen@dispostable.com");

    }

    @Test
    public void testToFetchByUser(){
        Users randomUser = new Users(1L,"random@dispostable.com", "123456");
        Users userSaveResponse = userRepository.save(randomUser);

        final LocalDateTime today = LocalDateTime.now();
        UserTOTP randomUserTOTP = new UserTOTP(1L, "0F0F0F", today, userSaveResponse);
        totpRepository.save(randomUserTOTP);

        UserTOTP userTOTP = totpRepository.findByUser(userSaveResponse);
        assertThat(userTOTP.getUser().getEmail()).isEqualToIgnoringCase(randomUser.getEmail());
    }

    @Test
    public void testToFetchByCode(){
        Users randomUser = new Users(1L,"random@dispostable.com", "123456");
        Users userSaveResponse = userRepository.save(randomUser);

        final LocalDateTime today = LocalDateTime.now();
        UserTOTP randomUserTOTP = new UserTOTP(1L, "0F0F0F", today, userSaveResponse);
        totpRepository.save(randomUserTOTP);

        UserTOTP userTOTP = totpRepository.findByCode(randomUserTOTP.getCode());
        assertThat(userTOTP.getUser().getEmail()).isEqualToIgnoringCase(randomUser.getEmail());
    }



}
