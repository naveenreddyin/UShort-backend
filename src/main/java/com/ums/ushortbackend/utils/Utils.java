package com.ums.ushortbackend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.net.URL;
import java.net.MalformedURLException;

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

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static boolean isUrlValid(String url) {
        boolean valid = true;
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            valid = false;
        }
        return valid;
    }
}
