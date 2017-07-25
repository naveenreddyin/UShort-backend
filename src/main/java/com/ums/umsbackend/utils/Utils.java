package com.ums.umsbackend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

/**
 * Created by Naveen on 17/07/2017.
 */
public class Utils {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isBetween(LocalDateTime check, LocalDateTime startTime, LocalDateTime endTime) {
        return ((check.equals(startTime) || check.isAfter(startTime)) &&
                (check.equals(endTime) || check.isBefore(endTime)));
    }
}
