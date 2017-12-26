package com.ums.ushortbackend.domains;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class ShortURLTest {

    @Test
    public void testIfSaving(){
        ShortURL shortURL = new ShortURL(1L, LocalDateTime.now(),
                "123456", "http://google.com", "http://localhost:8080");
        assertThat(shortURL.getCount()).isEqualTo(0);
    }
}
