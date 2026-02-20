package com.peacefulpagessanctuary.service;

import com.peacefulpagessanctuary.entity.CartItem;
import com.peacefulpagessanctuary.entity.Customer;
import com.peacefulpagessanctuary.entity.Product;
import com.peacefulpagessanctuary.exception.InvalidOperationException;
import com.peacefulpagessanctuary.exception.ResourceNotFoundException;
import com.peacefulpagessanctuary.repository.CartItemRepository;
import com.peacefulpagessanctuary.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartService(CartItemRepository cartItemRepository,
                       ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public CartItem addToCart(Customer customer, Long productId, int quantity) {

        if (quantity <= 0) {
            throw new InvalidOperationException("Quantity must be greater than 0");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        CartItem item = new CartItem();
        item.setCustomer(customer);
        item.setProduct(product);
        item.setQuantity(quantity);

        return cartItemRepository.save(item);
    }

    public List<CartItem> getCart(Customer customer) {
        return cartItemRepository.findByCustomer(customer);
    }

    public void clearCart(Customer customer) {
        cartItemRepository.deleteByCustomer(customer);
    }
}