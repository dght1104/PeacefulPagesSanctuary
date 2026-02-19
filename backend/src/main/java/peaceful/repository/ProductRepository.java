package peaceful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaceful.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
