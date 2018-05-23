package com.webapp.site;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.NotEmpty;

public class ChronologyForm {

	
	@NotEmpty
	String name;
	
	Long id;
	
	String category;
	
	@NotEmpty
	String eventList;
	
	@NotEmpty
	String description;
	
	String url;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEventList() {
		return eventList;
	}

	public void setEventList(String eventList) {
		this.eventList = eventList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
	
}
