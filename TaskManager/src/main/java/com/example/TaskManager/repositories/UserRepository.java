package com.example.TaskManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.TaskManager.entities.User;


public interface UserRepository extends CrudRepository<User, Integer> {
	
	public User findById(int id);
	
	public User findByEmail(String email);
}