package com.soulprincess.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {

    @Autowired
    private WishlistRepository wishlistRepository;

    // Add item to wishlist
    @PostMapping
    public Wishlist addToWishlist(@RequestBody Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    // Get all wishlist items
    @GetMapping
    public List<Wishlist> getAllWishlistItems() {
        return wishlistRepository.findAll();
    }

    // Get wishlist items for a specific user
    @GetMapping("/user/{userId}")
    public List<Wishlist> getWishlistByUser(@PathVariable Integer userId) {
        return wishlistRepository.findAll()
                .stream()
                .filter(w -> w.getUser().getId().equals(userId))
                .toList();
    }

    // Remove item from wishlist
    @DeleteMapping("/{id}")
    public String removeFromWishlist(@PathVariable Integer id) {
        wishlistRepository.deleteById(id);
        return "Item removed from wishlist";
    }
}