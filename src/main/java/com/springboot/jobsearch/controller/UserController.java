package com.springboot.jobsearch.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jobsearch.dto.UserDTO;
import com.springboot.jobsearch.dto.UserRegister;
import com.springboot.jobsearch.entity.Role;
import com.springboot.jobsearch.entity.User;
import com.springboot.jobsearch.exception.UserAlreadyExistsException;
import com.springboot.jobsearch.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody UserRegister userRegister) {
		try {
			// Register the user
			User newUser = userService.registerUser(userRegister);

			// Return a ResponseEntity with the created user and HTTP status 201
			return ResponseEntity.status(201).body(newUser);
		} catch (UserAlreadyExistsException e) {
			// Handle cases such as user already exists
			return ResponseEntity.status(409).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
		Optional<User> user = userService.getUserById(id);
		return user.map(u -> ResponseEntity.ok(new UserDTO(u))) // Convert User to UserDTO
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		try {
			boolean deleted = userService.deleteUser(id);
			if (deleted) {
				// Return 200 OK if user was successfully deleted
				return ResponseEntity.ok("User deleted successfully.");
			} else {
				// Return 404 Not Found if the user was not found
				return ResponseEntity.status(404).body("User not found.");
			}
		} catch (Exception e) {
			// Log the exception and return a 500 error
			e.printStackTrace();
			return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
		}
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable int userId, @RequestBody UserDTO userDTO) {
		System.out.println("user id = "+ userId);
		System.out.println("body = "+userDTO);
		try {
			UserDTO updatedUser = userService.updateUser(userId, userDTO);
			return ResponseEntity.ok(updatedUser);
		}
		catch (IllegalArgumentException e) {
			return ResponseEntity.status(400).body(null); // Bad Request if user not found
		}
		catch (Exception e) {
			return ResponseEntity.status(500).body(null); // Internal Server Error
		}
	}
}