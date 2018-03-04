package com.webapp.site.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.webapp.site.entities.Figure;

public interface FigureRepository extends CrudRepository<Figure,Long> {
	
	List<Figure> findByCategories_NameAndRoles_Name(String categoryName, String roleName);
	
	List<Figure> findByCategories_Name(String name);
	
	List<Figure> findByRoles_Name(String name);
}
