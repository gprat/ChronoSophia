package com.webapp.site;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import javax.inject.Inject;

import com.webapp.site.entities.User;
import com.webapp.site.repositories.UserRepository;

@Service
public class DefaultUserService implements UserService {

	@Inject UserRepository userRepository;
	
	@Inject PasswordEncoder passwordEncoder;
	
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
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		this.userRepository.save(user);
	}

	@Override
	public void delete(long id) {
		this.userRepository.delete(id);
	}
	
	@Override
	public User findByLogin(String login){
		return this.userRepository.findOneByLogin(login);
	}
	
	@Override
	public void deleteUserByLogin(String login){
		this.userRepository.delete(this.userRepository.findOneByLogin(login));
	}
	
	@Override
	public boolean isUserLoginUnique(Long id, String login) {
        User user = findByLogin(login);
        return ( user == null || ((id != null) && (user.getIdUser() == id)));
    }

}
