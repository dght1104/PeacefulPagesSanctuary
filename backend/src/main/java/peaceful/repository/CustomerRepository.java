package peaceful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaceful.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
