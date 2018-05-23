package com.webapp.site.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.webapp.site.entities.Figure;

public interface FigureRepository extends CrudRepository<Figure,Long> {
	
	List<Figure> findByCategories_NameAndRoles_NameAndUser_Login(String categoryName, String roleName, String login);
	
	List<Figure> findByCategories_NameAndUser_Login(String name, String login);
	
	List<Figure> findByRoles_NameAndUser_Login(String name, String login);
	
	List<Figure> findByUser_Login(String login);
}
