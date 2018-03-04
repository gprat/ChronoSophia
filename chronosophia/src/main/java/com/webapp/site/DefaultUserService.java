package com.webapp.site;

import java.util.List;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

import javax.inject.Inject;

import com.webapp.site.entities.User;
import com.webapp.site.repositories.UserRepository;

@Service
public class DefaultUserService implements UserService {

	@Inject UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
		List<User> list = new ArrayList<>();
		this.userRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public User getUser(long id) {
		return this.userRepository.findOne(id);
	}

	@Override
	public void save(User user) {
		this.userRepository.save(user);
	}

	@Override
	public void delete(long id) {
		this.userRepository.delete(id);
	}

}
