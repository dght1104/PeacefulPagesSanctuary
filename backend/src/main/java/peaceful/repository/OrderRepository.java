package peaceful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaceful.model.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
}
