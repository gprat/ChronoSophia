package com.webapp.site;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.webapp.site.entities.City;
import com.webapp.site.entities.Country;
import com.webapp.site.repositories.CityRepository;
import com.webapp.site.repositories.CountryRepository;

@Service
public class DefaultCityService implements CityService {
	@Inject CityRepository cityRepository;
	@Inject CountryRepository countryRepository;
	
	@Override
	public List<City> getAllCities() {
		List<City> list = new ArrayList<>();
        this.cityRepository.findAll().forEach(e -> list.add(e));
        return list;
	}

	@Override
	public City getCity(long id) {
		return this.cityRepository.findOne(id);
	}

	@Override
	public void save(City city) {
		this.cityRepository.save(city);

	}

	@Override
	public void deleteCity(long id) {
		this.cityRepository.delete(id);
	}
	
	@Override
	public void setCountry(City city,String countryname){
		Country country = countryRepository.getOneByName(countryname);
		if (country == null) {
			country = new Country();
			country.setName(countryname);
			countryRepository.save(country);
			country = countryRepository.getOneByName(countryname);
		}
		city.setCountry(country);
	}
	
	@Override
	public CityForm getCityForm(long id){
		City city = getCity(id);
		CityForm form = new CityForm();
		form.setIdCity(id);
		form.setCityname(city.getName());
		form.setCountryname(city.getCountry().getName());
		form.setLatitude(city.getLatitude());
		form.setLongitude(city.getLongitude());
		form.setDescription(city.getDescription());
		return form;
	}
	
	@Override
	public List<City> getCitiesByLogin(String login){
		return this.cityRepository.findByUser_Login(login);
	}
	
	@Override
	public List<City> getCitiesByEventYear(String login, int yearStart, int yearEnd){
		return this.cityRepository.findByUser_LoginAndEvents_Date_YearBetween(login, yearStart, yearEnd);
	}

}
