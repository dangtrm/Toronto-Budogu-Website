package backend4413.dev.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {

	private String productId;
	private int quantity;
	private double priceAtPurchase; // price at the time of purchase

	public OrderItem() {
	}

	public OrderItem(String productId, int quantity, double priceAtPurchase) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.priceAtPurchase = priceAtPurchase;
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

	public double getPriceAtPurchase() {
		return priceAtPurchase;
	}

	public void setPriceAtPurchase(double priceAtPurchase) {
		this.priceAtPurchase = priceAtPurchase;
	}

}
