package backend4413.dev.service;

import backend4413.dev.model.Order;
import backend4413.dev.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public List<Order> findByUserId(String userId) {
		return orderRepository.findByUserId(userId);
	}

	public Order createOrder(Order order) {
		order.totalAmountAndQuantity();
		return orderRepository.save(order);
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public Order findOrderByOrderId(String orderId) {
		return orderRepository.findById(orderId).orElse(null);
	}

	public void deleteOrder(String orderId) {
		orderRepository.deleteById(orderId);
	}

} 