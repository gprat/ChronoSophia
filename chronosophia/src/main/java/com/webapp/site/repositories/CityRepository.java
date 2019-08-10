package com.webapp.site.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.webapp.site.entities.City;

public interface CityRepository extends CrudRepository<City,Long> {

	List<City> findByUser_Login(String login);
	
	List<City> findByUser_LoginAndEvents_Date_YearBetween(String login, int yearStart, int yearEnd);
}
