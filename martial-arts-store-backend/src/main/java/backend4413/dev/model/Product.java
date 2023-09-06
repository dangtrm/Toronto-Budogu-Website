package backend4413.dev.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "Product")
public class Product {

	@Id
	private String id;
	private String name;
	private String category;
	private String info;
	private String imageURL;
	private int stockQuantity;
	private double price;
	private String brand;

	public Product() {
	}

	public Product(String name, String info, String category, String imageURL, int stockQuantity, double price, String brand) {
		this.name = name;
		this.info = info;
		this.category = category;
		this.imageURL = imageURL;
		this.stockQuantity = stockQuantity;
		this.price = price;
		this.brand = brand;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", info=" + info + ", category=" + category + ", imageURL="
				+ imageURL + ", stockQuantity=" + stockQuantity + ", price=" + price + "]";
	}

}
