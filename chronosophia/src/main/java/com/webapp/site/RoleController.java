package com.webapp.site;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.webapp.site.entities.Role;

@Controller
@RequestMapping("role")
public class RoleController {

	@Inject RoleService roleService;
	
	@RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
    public String list(Map<String, Object> model){
		model.put("roles",this.roleService.getAllRoles());
		return("role/list");
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String createRole(Map<String, Object> model){
		model.put("role", new Role());
		return ("role/add");
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public View createRole(Role role){
		this.roleService.save(role);
		return new RedirectView("/role/list", true, false);
	}
}
