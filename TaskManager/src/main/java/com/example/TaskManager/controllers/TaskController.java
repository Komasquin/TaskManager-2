package com.example.TaskManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.TaskManager.entities.Task;
import com.example.TaskManager.entities.User;
import com.example.TaskManager.services.TaskService;
import com.example.TaskManager.services.UserService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes({"task"})
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;

	Logger logger = LoggerFactory.getLogger(TaskController.class);

	@GetMapping("/create_task")
	public String getCreateTask(ModelMap model) {
		return "create_task";
	}

	@PostMapping(path = "/create_task", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String postCreateTask(@RequestParam MultiValueMap<String,String> paramMap, Principal principal, ModelMap model) throws ParseException, UnsupportedEncodingException {
		String notices = handleMissingFormInput(paramMap);
		if(notices != null) {
			notices = Base64.getEncoder().encodeToString(notices.getBytes());
			notices = URLEncoder.encode(notices, "UTF-8");
			return "redirect:/create_task?error=" + notices;
		}
		
		Task task = new Task();
		task.setName(paramMap.getFirst("tname"));
		task.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(paramMap.getFirst("sdate")));
		task.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(paramMap.getFirst("edate")));
		task.setDescription(paramMap.getFirst("desc"));
		task.setSeverity(paramMap.getFirst("sev"));
		
		Optional<User> foundUser = userService.GetUserByEmail(principal.getName());
		User user = foundUser.get();
		task.setUser(user);
		task.setEmail(user.getEmail());
		
		taskService.UpdateTask(task);
		return "redirect:/display_tasks?created=true";
	}

	@GetMapping("/delete_task")
	public String getDeleteTask(@RequestParam(value = "id", required = true) String id, ModelMap model) {
		int taskId = Integer.parseInt(id);
		Task task = taskService.GetTaskById(taskId);
		model.addAttribute("task", task);
		return "delete_task";
	}

	@PostMapping("/delete_task")
	public String postDeleteTask(ModelMap model) {
		Task task = (Task) model.getAttribute("task");
		taskService.DeleteTask(task);
		return "redirect:/display_tasks?deleted=true";
	}

	@GetMapping("/display_tasks")
	public String getDisplayTasks(@RequestParam(value = "created", required = false) String created,
			@RequestParam(value = "deleted", required = false) String deleted,
			@RequestParam(value = "updated", required = false) String updated, ModelMap model) {
		String notice = null;
		if (created != null) {
			if (created.equals("true")) notice = "Successfully created the task.";
			if (created.equals("false")) notice = "Failed to create the task.";
		} else if (deleted != null) {
			if (deleted.equals("true")) notice = "Successfully deleted the task.";
			if (deleted.equals("false")) notice = "Failed to delete the task.";
		} else if (updated != null) {
			if (updated.equals("true")) notice = "Successfully updated the task.";
			if (updated.equals("false")) notice = "Failed to update the task.";
		}
		model.addAttribute("notice", (notice != null) ? notice : "");

		logger.info("Getting all tasks...");
		Iterable<Task> tasks = taskService.GetAllTasks();

		logger.info("Passing tasks to view...");
		model.addAttribute("tasks", tasks);

		return "display_tasks";
	}

	@GetMapping("/update_task")
	public String getUpdateTask(@RequestParam(value = "id", required = true) String id, ModelMap model) {
		int taskId = Integer.parseInt(id);
		Task task = taskService.GetTaskById(taskId);
		model.addAttribute("task", task);
		return "update_task";
	}

	@PostMapping(path = "/update_task", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String postUpdateTask(@RequestParam MultiValueMap<String,String> paramMap, ModelMap model) throws ParseException, UnsupportedEncodingException {
		String notices = handleMissingFormInput(paramMap);
		if(notices != null) {
			notices = Base64.getEncoder().encodeToString(notices.getBytes());
			notices = URLEncoder.encode(notices, "UTF-8");
			return "redirect:/update_task?error=" + notices;
		}
		
		Task task = (Task) model.getAttribute("task");
		task.setName(paramMap.getFirst("tname"));
		task.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(paramMap.getFirst("sdate")));
		task.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(paramMap.getFirst("edate")));
		task.setDescription(paramMap.getFirst("desc"));
		task.setSeverity(paramMap.getFirst("sev"));
		taskService.UpdateTask(task);
		return "redirect:/display_tasks?updated=true";
	}
	
	public String handleMissingFormInput(MultiValueMap<String,String> paramMap) {
		String warnings = "";
		if(paramMap.getFirst("tname").equals(""))
			warnings += "Error: missing task name<br>";
		if(paramMap.getFirst("sdate").equals(""))
			warnings += "Error: missing start date<br>";
		if(paramMap.getFirst("edate").equals(""))
			warnings += "Error: missing end date<br>";
		if(paramMap.getFirst("desc").equals(""))
			warnings += "Error: missing description<br>";
		if(paramMap.getFirst("desc").equals(""))
			warnings += "Error: missing severity<br>";
		return (warnings.length() > 0) ? warnings.substring(0, warnings.length() - 4) : null;
	}

}