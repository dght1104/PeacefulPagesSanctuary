package peaceful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaceful.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
