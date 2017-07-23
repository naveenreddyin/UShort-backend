package com.ums.umsbackend.repositories;

import com.ums.umsbackend.domains.Duration;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Naveen on 20/07/2017.
 */
public interface DurationConfigRespository extends CrudRepository<Duration, Long> {
    Duration findFirstByOrderByIdAsc();
}
