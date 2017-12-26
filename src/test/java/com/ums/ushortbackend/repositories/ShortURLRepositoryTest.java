package com.ums.ushortbackend.repositories;

import static org.assertj.core.api.Assertions.*;

import com.ums.ushortbackend.domains.ShortURL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShortURLRepositoryTest {

    @Autowired
    private ShortURLRepository shortURLRepository;

    @Test
    public void testToSaveShortURL(){
        final ShortURL shortURL = new ShortURL(1L, LocalDateTime.now(),
                "123456", "http://google.com", "http://localhost:8080/");
        shortURLRepository.save(shortURL);
        assertThat(shortURLRepository.count()).isGreaterThan(0);
    }


}
