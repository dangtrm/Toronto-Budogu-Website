package backend4413.dev.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class CartItem {

	private String productId;
	private String productName;
	private int quantity;
	private double price;
	private double totalPrice;
	
	
	
//	public CartItem(String productId, String productName, int quantity, double totalPrice) {
//		super();
//		this.productId = productId;
//		this.productName = productName;
//		this.quantity = quantity;
//		this.totalPrice = totalPrice;
//	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public CartItem(String productId, int quantity, double price) {
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}
	
	public CartItem() {
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, quantity, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		return Objects.equals(productId, other.productId) && quantity == other.quantity
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}

	public String toString() {
		return "CartItem [productId=" + productId + ", quantity=" + quantity + ", price=" + price + "]";
	}

}
