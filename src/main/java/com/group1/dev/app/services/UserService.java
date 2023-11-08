package com.group1.dev.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.group1.dev.app.model.dao.UserRepository;
import com.group1.dev.app.model.entity.EntityUser;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	
	@Override
	public List<EntityUser> findAll() {
		
		return (List<EntityUser>) userRepository.findAll();
	}

	@Override
	public Optional<EntityUser> findById(int id) {
	
		return userRepository.findById(id);
	}

	@Override
	public void save(EntityUser user) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		userRepository.save(user);

	}

	@Override
	public void deleteById(int id) {

		userRepository.deleteById(id);

	}
	
		@Override
		public EntityUser findUser(String username, String password) {
		
			return userRepository.findUser(username, password);
	}

	public Optional<EntityUser> findByUsername(String username){
		
		return userRepository.findByUsername(username);
	
	}

}
