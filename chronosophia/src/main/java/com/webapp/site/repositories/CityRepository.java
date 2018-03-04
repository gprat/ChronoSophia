package com.webapp.site.repositories;

import org.springframework.data.repository.CrudRepository;

import com.webapp.site.entities.City;

public interface CityRepository extends CrudRepository<City,Long> {

}
