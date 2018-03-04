package com.webapp.site.repositories;

import org.springframework.data.repository.CrudRepository;

import com.webapp.site.entities.Role;

public interface RoleRepository extends CrudRepository<Role,Long>{

	Role getOneByName (String name);
}
