package peaceful.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peaceful.dto.CartRequest;
import peaceful.model.CartItem;
import peaceful.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * Add product to cart
     */
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody CartRequest request) {

        cartService.addToCart(
                request.getCustomerId(),
                request.getProductId(),
                request.getQuantity()
        );

        return ResponseEntity.ok("Product added to cart successfully");
    }

    /**
     * Update quantity
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateQuantity(@RequestBody CartRequest request) {

        cartService.updateQuantity(
                request.getCustomerId(),
                request.getProductId(),
                request.getQuantity()
        );

        return ResponseEntity.ok("Cart updated successfully");
    }

    /**
     * Remove item
     */
    @DeleteMapping("/remove")
    public ResponseEntity<?> removeItem(
            @RequestParam Integer customerId,
            @RequestParam Integer productId
    ) {

        cartService.removeItem(customerId, productId);

        return ResponseEntity.ok("Item removed successfully");
    }

    /**
     * Get cart by customer
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<List<CartItem>> getCart(
            @PathVariable Integer customerId
    ) {

        return ResponseEntity.ok(
                cartService.getCartByCustomer(customerId)
        );
    }
}
