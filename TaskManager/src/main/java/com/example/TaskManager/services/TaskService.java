package com.example.TaskManager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskManager.entities.Task;
import com.example.TaskManager.repositories.TaskRepository;


@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public Iterable<Task> GetAllTasks() {
		return taskRepository.findAll();
	}
	
	public Task GetTaskById(int id) {
		Optional<Task> foundTask = Optional.ofNullable(taskRepository.findById(id));
		return (foundTask.get());
	}
	
	public void DeleteTask(Task taskToDelete) {
		taskRepository.delete(taskToDelete);
	}
	
	public void UpdateTask(Task taskToUpdate) {
		taskRepository.save(taskToUpdate);
	}
}