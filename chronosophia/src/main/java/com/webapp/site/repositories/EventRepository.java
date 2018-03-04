package com.webapp.site.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.webapp.site.entities.Event;

public interface EventRepository extends CrudRepository<Event,Long>, JpaSpecificationExecutor<Event> {
	
	List<Event> findDistinctByCategories_IdCategoryIn(List<Long> categories);
	
	List<Event> findByIdEventIn(List<Long> idEvents);
}
