package backend4413.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend4413.dev.model.User;
import backend4413.dev.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(@Autowired UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findUser(String id) {
		return userRepository.findById(id);
	}

	public void deleteAll() {
		userRepository.deleteAll();
	}

	public User addUser(User user) {
		return userRepository.insert(user);
	}

//    public void insertUserSampleData() {
//        userRepository.saveAll(Collections.singleton(new User("user1", "Ravi Chaudhary", "ravi7131@yahoo.com")));
//    }
}
