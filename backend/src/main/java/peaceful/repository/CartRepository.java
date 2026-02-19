package peaceful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import peaceful.model.CartItem;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Integer> {

    @Procedure(procedureName = "sp_AddToCart")
    void addToCart(
            @Param("cus_id") Integer customerId,
            @Param("prod_id") Integer productId,
            @Param("quantity") Integer quantity
    );

    @Procedure(procedureName = "sp_UpdateCartQuantity")
    void updateCartQuantity(
            @Param("cus_id") Integer customerId,
            @Param("prod_id") Integer productId,
            @Param("quantity") Integer quantity
    );

    @Procedure(procedureName = "sp_RemoveFromCart")
    void removeFromCart(
            @Param("cus_id") Integer customerId,
            @Param("prod_id") Integer productId
    );

    @Query("SELECT c FROM CartItem c WHERE c.customer.id = :customerId")
    List<CartItem> findByCustomerId(@Param("customerId") Integer customerId);
}
