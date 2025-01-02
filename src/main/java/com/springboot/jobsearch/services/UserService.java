package com.springboot.jobsearch.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.jobsearch.dto.UserRegister;
import com.springboot.jobsearch.entity.Role;
import com.springboot.jobsearch.entity.User;
import com.springboot.jobsearch.exception.UserAlreadyExistsException;
import com.springboot.jobsearch.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Optional<User> getUserById(int id) {
		return userRepository.findById(id);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User registerUser(UserRegister userRegister) {
		if (userRepository.existsByEmail(userRegister.getEmail())) {
			throw new UserAlreadyExistsException("User with this email already exists.");
		}

		User user = new User(userRegister.getName(), userRegister.getEmail(), userRegister.getPassword(),
				Role.valueOf(userRegister.getRole().toUpperCase()));
		return userRepository.save(user);
	}

	public boolean deleteUser(int id) {
		// Check if the user exists
		if (userRepository.existsById(id)) {
			// If the user exists, delete the user
			userRepository.deleteById(id);
			return true;
		}
		// Return false if the user was not found
		return false;
	}

}
