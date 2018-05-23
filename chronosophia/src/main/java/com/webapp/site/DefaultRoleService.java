package com.webapp.site;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.webapp.site.entities.Role;
import com.webapp.site.repositories.RoleRepository;

@Service
public class DefaultRoleService implements RoleService {

	@Inject RoleRepository roleRepository;
	
	@Override
	public List<Role> getAllRoles() {
		List<Role> list = new ArrayList<>();
		roleRepository.findAll().forEach(e->list.add(e));
		return list;
	}

	@Override
	public Role getRole(long id) {
		return roleRepository.findOne(id);
	}

	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}

	@Override
	public void delete(long id) {
		roleRepository.delete(id);
	}
	
	@Override
	public Role getRole(String name){
		return roleRepository.getOneByName(name);
	}
	
	@Override
	public List<Role> getRolesByLogin(String login){
		return this.roleRepository.findByUser_Login(login);
	}

}
