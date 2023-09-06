package backend4413.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend4413.dev.model.Product;
import backend4413.dev.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> getProducts() {
		return productService.findAll();
	}

	@GetMapping(value = "byId/{id}")
	public Product getProduct(@PathVariable("id") String id) throws NotFoundException {
		return productService.findProduct(id).orElseThrow(() -> new NotFoundException());
	}

	@GetMapping(value = "byCategory/{category}")
	public List<Product> getProductsByType(@PathVariable("category") String type) {
		return productService.findByCategory(type);
	}
	
	@GetMapping(value = "byKeyword/{keyword}")
	public List<Product> getProductsByKeyword(@PathVariable("keyword") String keyword) {
		return productService.searchProductsByKeyword(keyword);
	}

    //get products by brand
    @GetMapping(value = "byBrand/{brand}")
    public List<Product> getProductsByBrand(@PathVariable("brand") String brand) {
        return productService.findByBrand(brand);
    }
}
