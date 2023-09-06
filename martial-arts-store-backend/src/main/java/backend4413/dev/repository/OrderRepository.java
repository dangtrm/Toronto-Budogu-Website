package backend4413.dev.repository;

import backend4413.dev.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    //find order by user id
    public List<Order> findByUserId(String userId);

}