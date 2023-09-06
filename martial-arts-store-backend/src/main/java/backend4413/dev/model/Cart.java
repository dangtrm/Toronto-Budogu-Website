package backend4413.dev.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Cart")
@Getter
@Setter
public class Cart {

	@Id
	private String id;

	private String userId;

	private Integer quantity;

	private Double totalAmount;

	private List<CartItem> cartItems;

	public Cart() {
	}

	public Cart(String userId, List<CartItem> cartItems) {
		this.userId = userId;
		this.cartItems = cartItems;
	}

	public void totalAmountAndQuantity() {
		totalAmount = 0.0;
		quantity = 0;
		for (CartItem item : cartItems) {
			totalAmount += item.getPrice() * item.getQuantity();
			quantity += item.getQuantity();
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", quantity=" + quantity + ", totalAmount=" + totalAmount
				+ ", cartItems=" + cartItems + "]";
	}
}
