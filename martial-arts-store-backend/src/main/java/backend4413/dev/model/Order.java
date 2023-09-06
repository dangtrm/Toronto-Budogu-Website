package backend4413.dev.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "Order")
@Getter
@Setter
public class Order {

	@Id
	private String id;
	private String userId;
	private List<OrderItem> orderItems = new ArrayList<>();
	private double totalAmount;
	private int quantity;
	private Date orderDate;

	public Order() {
		this.orderDate = new Date();
	}

	public Order(String userId, List<OrderItem> orderItems) {
		this.userId = userId;
		this.orderItems = orderItems;
		this.orderDate = new Date();
		this.totalAmountAndQuantity();
	}

	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
	}

	public void totalAmountAndQuantity() {
		totalAmount = 0.0;
		quantity = 0;
		for (OrderItem item : orderItems) {
			totalAmount += item.getPriceAtPurchase() * item.getQuantity();
			quantity += item.getQuantity();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

}
