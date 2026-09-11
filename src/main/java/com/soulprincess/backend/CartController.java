package com.soulprincess.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    // Add item to cart
    @PostMapping
    public Cart addToCart(@RequestBody Cart cart) {
        return cartRepository.save(cart);
    }

    // Get all cart items
    @GetMapping
    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }

    // Get cart items for a specific user
    @GetMapping("/user/{userId}")
    public List<Cart> getCartByUser(@PathVariable Integer userId) {
        return cartRepository.findAll()
                .stream()
                .filter(c -> c.getUser().getId().equals(userId))
                .toList();
    }

    // Remove item from cart
    @DeleteMapping("/{id}")
    public String removeFromCart(@PathVariable Integer id) {
        cartRepository.deleteById(id);
        return "Item removed from cart";
    }

    // Update quantity
    @PutMapping("/{id}")
    public Cart updateCartQuantity(@PathVariable Integer id, @RequestBody Cart updatedCart) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart != null) {
            cart.setQuantity(updatedCart.getQuantity());
            return cartRepository.save(cart);
        }
        return null;
    }
}