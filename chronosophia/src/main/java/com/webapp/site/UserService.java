package com.webapp.site;

import java.util.List;

import com.webapp.site.entities.User;

public interface UserService {
	
	List<User> getAllUsers();
	
	User getUser(long id);
	
	void save(User user);
	
	void delete(long id);
	
	User findByLogin(String login);
	
	void deleteUserByLogin(String login);
	
	boolean isUserLoginUnique(Long id, String login);

}
