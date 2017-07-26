package com.ums.umsbackend.domains;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;


/**
 * Created by Naveen on 26/07/2017.
 */
public class UsersTest {

    @Test
    public void testIfHasEnabled(){
        Users user = new Users(1L, "some@som.com", "123456");
//        assertThat(user.isEnabled())
    }

}