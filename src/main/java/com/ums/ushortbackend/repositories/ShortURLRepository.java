package com.ums.ushortbackend.repositories;

import com.ums.ushortbackend.domains.ShortURL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface ShortURLRepository extends CrudRepository<ShortURL, Long>{

    ShortURL findByCode(@Param("code") String code);

    Long findById(@Param("id") Long id);

    Collection<ShortURL> findAll();


}
