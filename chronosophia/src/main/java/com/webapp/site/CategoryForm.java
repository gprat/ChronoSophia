package com.webapp.site;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.NotEmpty;

public class CategoryForm {

	@NotEmpty
	private String CategoryName;

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	
	
}
