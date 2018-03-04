package com.webapp.site;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapp.site.entities.Category;
import com.webapp.site.entities.Figure;
import com.webapp.site.entities.Role;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.io.IOException;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("figure")
public class FigureController {
	
	@Inject FigureService figureService;
	@Inject DateService dateService;
	@Inject UserService userService;
	@Inject RoleService roleService;
	@Inject CategoryService categoryService;
	@Inject ObjectMapper objectMapper;
	
	
	@RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Map<String, Object> model){
		model.put("selectFigureForm",new SelectFigureForm());
		model.put("figures",this.figureService.getAllFigures() );
		model.put("categoryList", this.categoryService.getAllCategories());
		model.put("roleList", this.roleService.getAllRoles());
		return "figure/list";
	}
	
	@RequestMapping(value = "list",method = RequestMethod.POST)
	public String list(@ModelAttribute("selectFigureForm") SelectFigureForm selectFigureForm,Map<String, Object> model ){
		model.put("selectFigureForm",new SelectFigureForm());
		model.put("figures",this.figureService.getFiguresByCategoryAndRole(selectFigureForm.category, selectFigureForm.role));
		model.put("categoryList", this.categoryService.getAllCategories());
		model.put("roleList", this.roleService.getAllRoles());
		return "figure/list";
	}
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateFigureForm(@PathVariable("id") long id, Model model) {
		FigureForm figureForm = this.figureService.getFigureForm(id);
		try{
			model.addAttribute("categoriesJSON", objectMapper.writeValueAsString(this.categoryService.getAllCategories()));
			model.addAttribute("rolesJSON", objectMapper.writeValueAsString(this.roleService.getAllRoles()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		model.addAttribute("figureForm", figureForm);
		return "figure/figureform";

	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String createFigure(Map<String, Object> model){
		 model.put("figureForm", new FigureForm());
		 try{
				model.put("categoriesJSON", objectMapper.writeValueAsString(this.categoryService.getAllCategories()));
				model.put("rolesJSON", objectMapper.writeValueAsString(this.roleService.getAllRoles()));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		 return "figure/figureform";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public View createFigure(FigureForm form){
		Figure figure;
		if(form.id!=null && form.id!=0){
			figure = this.figureService.getFigure(form.id);
		}else{
			figure = new Figure();
		} 
		List<Category> categoryList = new ArrayList<>();
		List<Role>	roleList = new ArrayList<>();
		figure.setFirstName(form.firstName);
		figure.setLastName(form.lastName);
		figure.setBirthDate(this.dateService.setDate(form.dayOfBirth, form.monthOfBirth, form.yearOfBirth));
		figure.setDeathDate(this.dateService.setDate(form.dayOfDeath, form.monthOfDeath, form.yearOfDeath));
		figure.setUser(this.userService.getUser(1));
		if(form.categories!=null){
			new ArrayList<String>(Arrays.asList(form.categories.split(","))).forEach(idCategory->categoryList.add(categoryService.getCategory(Long.parseLong(idCategory))));
		}
		if(form.roles!=null){
			new ArrayList<String>(Arrays.asList(form.roles.split(","))).forEach(idRole->roleList.add(roleService.getRole(Long.parseLong(idRole))));
		}
		figure.setCategories(categoryList);
		figure.setRoles(roleList);
		this.figureService.save(figure);
		return new RedirectView("/figure/list", true, false);
	}
	
	@RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
	public View deleteFigure(@PathVariable("id") long id){
		this.figureService.delete(id);
		return new RedirectView("/figure/list", true, false);
	}
}
