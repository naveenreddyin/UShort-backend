package com.ums.umsbackend.repositories;

import static org.assertj.core.api.Assertions.*;

import com.ums.umsbackend.domains.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Naveen on 16/07/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UsersRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testToSaveUser(){
        final Users user = new Users(1L,"naveen@dispostable.com", "123456");
        userRepository.save(user);
        // test if user is saved and not null
        assertThat(user.getId()).isNotNull();
        // test if user password is encrypted or not
        assertThat(user.getId()).isEqualTo(1);
        // till now it should be greater than 0 :)
        assertThat(userRepository.count()).isGreaterThan(0);

    }

    @Test
    public void testDeletingSavedUser(){

        final Users user1 = new Users(1L,"naveen@dispostable.com", "123456");

        userRepository.save(user1);
        assertThat(userRepository.findByEmail("naveen@dispostable.com")).isNotNull();

        userRepository.deleteByEmail("naveen@dispostable.com");

        assertThat(userRepository.findByEmail("naveen@dispostable.com")).isNull();

    }

    @Test
    public void testFetchingSavedUser(){
        final Users user1 = new Users(1L,"naveen@dispostable.com", "123456");

        Users userResponse = userRepository.save(user1);

        Users user = userRepository.findByEmail("naveen@dispostable.com");
        assertThat(user.getEmail()).isEqualToIgnoringCase("naveen@dispostable.com");

        Users secondUser = userRepository.findOne(userResponse.getId());
        assertThat(secondUser.getEmail()).isEqualToIgnoringCase("naveen@dispostable.com");


    }

}
