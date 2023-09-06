package backend4413.dev.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import backend4413.dev.model.Product;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

	// get all products by category
	public List<Product> findByCategory(String category);

	// get the products if an user searches using the search bar with a keyword the
	// method will return the products that contain the keyword
	public List<Product> findByNameContainingIgnoreCase(String keyword);

	List<Product> findByBrand(String brand);
}
