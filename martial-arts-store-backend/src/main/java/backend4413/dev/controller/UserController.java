package backend4413.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend4413.dev.model.User;
import backend4413.dev.service.UserService;


@RestController
@RequestMapping(value = "user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value = "/allUsers")
	public List<User> getUsers() {
		return userService.findAll();
	}
	
	@GetMapping(value = "byId/{id}")
	public User getUser(@PathVariable String id) throws NotFoundException {
		return userService.findUser(id).orElseThrow(() -> new NotFoundException());
	}
	
	@PostMapping(value = "/addUser")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
}
