package peaceful.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaceful.model.CartItem;
import peaceful.repository.CartRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    @Transactional
    public void addToCart(Integer customerId, Integer productId, Integer quantity) {

        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        cartRepository.addToCart(customerId, productId, quantity);
    }

    @Transactional
    public void updateQuantity(Integer customerId, Integer productId, Integer quantity) {

        cartRepository.updateCartQuantity(customerId, productId, quantity);
    }

    @Transactional
    public void removeItem(Integer customerId, Integer productId) {

        cartRepository.removeFromCart(customerId, productId);
    }

    public List<CartItem> getCartByCustomer(Integer customerId) {

        return cartRepository.findByCustomerId(customerId);
    }
}
