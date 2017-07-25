package com.ums.umsbackend.utils;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by Naveen on 25/07/2017.
 */
public class UtilsTest {
    @Test
    public void isBetween() throws Exception {

        LocalDateTime start = LocalDateTime.now();
        start = start.withDayOfMonth(25).withHour(13).withMinute(42).withSecond(0).withNano(0);
        LocalDateTime end = start.plusDays(2);

        LocalDateTime check = LocalDateTime.now();

        assertThat(Utils.isBetween(check, start, end)).isTrue();

    }

}