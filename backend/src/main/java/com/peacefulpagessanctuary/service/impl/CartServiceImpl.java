package com.peacefulpagessanctuary.service.impl;

import com.peacefulpagessanctuary.dto.request.cart.AddToCartRequest;
import com.peacefulpagessanctuary.dto.request.cart.UpdateCartItemRequest;
import com.peacefulpagessanctuary.dto.response.cart.CartResponse;

import com.peacefulpagessanctuary.exception.ResourceNotFoundException;

import com.peacefulpagessanctuary.mapper.CartItemMapper;

import com.peacefulpagessanctuary.model.CartItem;
import com.peacefulpagessanctuary.model.Customer;
import com.peacefulpagessanctuary.model.Product;

import com.peacefulpagessanctuary.repository.CartItemRepository;
import com.peacefulpagessanctuary.repository.CustomerRepository;
import com.peacefulpagessanctuary.repository.ProductRepository;

import com.peacefulpagessanctuary.service.CartService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public CartResponse getCartByCustomer(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found"));

        List<CartItem> cartItems = cartItemRepository.findByCustomer(customer);

        return cartItemMapper.toCartResponse(cartItems);
    }

    @Override
    public CartResponse addToCart(Long customerId,
            AddToCartRequest request) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found"));

        Product product = productRepository.findById(
                request.getProductId()).orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Product not found"));

        CartItem cartItem = CartItem.builder()
                .customer(customer)
                .product(product)
                .quantity(request.getQuantity())
                .build();

        cartItemRepository.save(cartItem);

        List<CartItem> cartItems = cartItemRepository.findByCustomer(customer);

        return cartItemMapper.toCartResponse(cartItems);
    }

    @Override
    public CartResponse updateCartItem(Long cartItemId,
            UpdateCartItemRequest request) {

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart item not found"));

        cartItem.setQuantity(request.getQuantity());

        cartItemRepository.save(cartItem);

        List<CartItem> cartItems = cartItemRepository.findByCustomer(
                cartItem.getCustomer());

        return cartItemMapper.toCartResponse(cartItems);
    }

    @Override
    public void removeCartItem(Long cartItemId) {

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart item not found"));

        cartItemRepository.delete(cartItem);
    }

    @Override
    public void clearCart(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found"));

        List<CartItem> cartItems = cartItemRepository.findByCustomer(customer);

        cartItemRepository.deleteAll(cartItems);
    }
}