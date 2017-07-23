package com.ums.umsbackend.repositories;

import com.ums.umsbackend.domains.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Naveen on 16/07/2017.
 */
@RepositoryRestResource
public interface UserRepository extends CrudRepository<Users, Long> {
    Long deleteByEmail(String email);

    Users findByEmail(@Param("email") String email);
}
