package com.ums.umsbackend.repositories;

import com.ums.umsbackend.domains.UserTOTP;
import com.ums.umsbackend.domains.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Naveen on 18/07/2017.
 */
public interface TOTPRepository extends CrudRepository<UserTOTP, Long> {

    UserTOTP findByUser(Users user);

    UserTOTP findUserByUserEmail(@Param("email") String email);

    Long deleteUserByUserEmail(String email);

    UserTOTP findByCode(String code);
}
