package backend4413.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import backend4413.dev.model.Product;
import backend4413.dev.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Optional<Product> findProduct(String id) {
		return productRepository.findById(id);
	}

	public void deleteProduct(String id) {
		productRepository.deleteById(id);
	}

	public Product createProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	// get all products by category
	public List<Product> findByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	// get the products if an user searches using the search bar with a keyword the
	// method will return the products that contain the keyword
	public List<Product> searchProductsByKeyword(String keyword) {
		return productRepository.findByNameContainingIgnoreCase(keyword);
	}

	public List<Product> findByBrand(String brand) {
		return productRepository.findByBrand(brand);
	}

}
