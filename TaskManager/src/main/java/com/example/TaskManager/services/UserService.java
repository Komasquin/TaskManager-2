package com.example.TaskManager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskManager.entities.User;
import com.example.TaskManager.repositories.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Iterable<User> GetAllUsers() {
		return userRepository.findAll();
	}
	
	public User GetUserById(int id) {
		Optional<User> foundUser = Optional.ofNullable(userRepository.findById(id));
		return (foundUser.get());
	}
	
	public Optional<User> GetUserByEmail(String email) {
		Optional<User> foundUser = Optional.ofNullable(userRepository.findByEmail(email));
		return foundUser;
	}
	
	public void DeleteUser(User userToDelete) {
		userRepository.delete(userToDelete);
	}
	
	public void UpdateUser(User userToUpdate) {
		userRepository.save(userToUpdate);
	}
}