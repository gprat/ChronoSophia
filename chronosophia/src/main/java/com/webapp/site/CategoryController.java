package com.webapp.site;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.webapp.site.entities.Category;


@Controller
@RequestMapping("category")
public class CategoryController {
	
	@Inject CategoryService categoryService;
	
	@RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Map<String, Object> model){
		model.put("categories", this.categoryService.getAllCategories());
		return "category/list";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String createCategory(Map<String, Object> model){
		model.put("categoryForm",new CategoryForm());
		System.out.println("test");
		return("category/add");
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public View createCategory(CategoryForm categoryForm){
		Category category = new Category();
		category.setName(categoryForm.getCategoryName());
		this.categoryService.save(category);
		return new RedirectView("/category/list", true, false);
	}
	

}
