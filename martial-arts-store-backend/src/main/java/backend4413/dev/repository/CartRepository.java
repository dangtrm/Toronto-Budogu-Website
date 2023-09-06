package backend4413.dev.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import backend4413.dev.model.Cart;

public interface CartRepository extends MongoRepository<Cart, String> {

	public Optional<Cart> findByUserId(String userId);
	
	public void deleteByUserId(String userId);
}
