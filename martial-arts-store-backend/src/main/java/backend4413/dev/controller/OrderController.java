package backend4413.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend4413.dev.model.Order;
import backend4413.dev.service.OrderService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public List<Order> getAllOrders() {
		return orderService.findAll();
	}
	
	@GetMapping(value = "/{userId}")
	public List<Order> getAllOrdersByUserId(@PathVariable String userId) {
		return orderService.findByUserId(userId);
	}
}
