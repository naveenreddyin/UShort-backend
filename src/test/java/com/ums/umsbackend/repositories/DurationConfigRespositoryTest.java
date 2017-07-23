package com.ums.umsbackend.repositories;

import static org.assertj.core.api.Assertions.*;

import com.ums.umsbackend.domains.Duration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Naveen on 20/07/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class DurationConfigRespositoryTest {

    @Autowired
    private DurationConfigRespository durationConfigRespository;

    private Duration duration;

    @Before
    public void setUp() throws Exception {

        duration = new Duration(1L, 30);
    }

    @Test
    public void testToSaveTimeDuration(){
        durationConfigRespository.save(duration);
    }

    @Test
    public void testToFetchFirstEntry(){
        final Duration duration2 = new Duration(2L, 20);
        final Duration duration3 = new Duration(3L, 5);

        durationConfigRespository.save(duration2);
        durationConfigRespository.save(duration3);

        Duration firstDuration = durationConfigRespository.findFirstByOrderByIdAsc();
        assertThat(firstDuration.getTimeDuration()).isEqualTo(duration2.getTimeDuration());
    }

}