package com.ums.umsbackend.controllers;

import com.ums.umsbackend.domains.Duration;
import com.ums.umsbackend.domains.UserTOTP;
import com.ums.umsbackend.domains.Users;
import com.ums.umsbackend.repositories.DurationConfigRespository;
import com.ums.umsbackend.repositories.TOTPRepository;
import com.ums.umsbackend.repositories.UserRepository;
import com.ums.umsbackend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Created by Naveen on 19/07/2017.
 */
@RestController
@RequestMapping("/api")
public class UMSApiController {

    @Autowired
    private TOTPRepository totpRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DurationConfigRespository durationConfigRespository;

    @RequestMapping(value = "/usertotp", method = RequestMethod.POST)
    public ResponseEntity<UserTOTP> createUserTOTP(@RequestParam("userId") String userId, @RequestParam("code") String code){

        System.out.println(userId);
        final LocalDateTime localDateTime = LocalDateTime.now();
        Users user = userRepository.findOne(Long.parseLong(userId));

        UserTOTP getUser = totpRepository.findByUser(user);

        if(getUser == null){
            UserTOTP userTOTP = new UserTOTP(1L, code, localDateTime, user);
            UserTOTP userTOTPResult = totpRepository.save(userTOTP);

            return new ResponseEntity<UserTOTP>(userTOTPResult, HttpStatus.CREATED);
        }

        return new ResponseEntity<UserTOTP>(getUser, HttpStatus.CONFLICT);

    }

    @RequestMapping(value = "/validate/{code}", method = RequestMethod.GET)
    public ResponseEntity<UserTOTP> validateCode(@PathVariable("code") String code){

        UserTOTP userTOTP = totpRepository.findByCode(code);
        LocalDateTime createdTime = userTOTP.getCreationTime();
        LocalDateTime now = LocalDateTime.now();

        Duration duration = durationConfigRespository.findFirstByOrderByIdAsc();

        LocalDateTime endTime = createdTime.plusMinutes(Long.valueOf(duration.getTimeDuration()));

        boolean result = Utils.isBetween(now, createdTime, endTime);

        System.out.println(""+result);

        if(result)
            return new ResponseEntity<UserTOTP>(userTOTP, HttpStatus.OK);

        return new ResponseEntity<UserTOTP>(userTOTP, HttpStatus.BAD_REQUEST);
    }
}
