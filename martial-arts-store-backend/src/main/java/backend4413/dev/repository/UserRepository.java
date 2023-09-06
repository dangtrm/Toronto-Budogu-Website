package backend4413.dev.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import backend4413.dev.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	Optional<User> findByUsername(String username);

}
