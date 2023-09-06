package backend4413.dev.security.token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token, String> {
	
	List<Token> findAllValidTokensByUserId(String userId);
	
	Optional<Token> findByToken(String token);
}
