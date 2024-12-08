package com.Chubb.EcommerceWebSite.service;

import com.Chubb.EcommerceWebSite.model.CartItem;
import com.Chubb.EcommerceWebSite.model.Customer;
import com.Chubb.EcommerceWebSite.model.Item;
import com.Chubb.EcommerceWebSite.repo.CartItemRepository;
import com.Chubb.EcommerceWebSite.repo.CustomerRepository;
import com.Chubb.EcommerceWebSite.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    // Get all cart items for a specific customer and populate customer and item
    public List<CartItem> getCartItemsByCustomerId(String customerId) {
        // Fetch all cart items by customer ID
        List<CartItem> cartItems = cartItemRepository.findByCustomerId(customerId);

        // Populate the customer and item for each cart item
        for (CartItem cartItem : cartItems) {
            // Populate the customer
            Optional<Customer> customerOpt = customerRepository.findById(cartItem.getCustomerId());
            customerOpt.ifPresent(cartItem::setCustomer);

            // Populate the item
            Optional<Item> itemOpt = itemRepository.findById(cartItem.getItemId());
            itemOpt.ifPresent(cartItem::setItem);
        }

        return cartItems;
    }

    // Add a new cart item or update existing ones
    public CartItem addCartItem(CartItem newCartItem) {

        // Check if cart item already exists for this customer
        List<CartItem> existingCartItems = cartItemRepository.findByCustomerId(newCartItem.getCustomerId());

        // If cart items exist, check and update
        for (CartItem existingCartItem : existingCartItems) {
            if (existingCartItem.getItemId().equals(newCartItem.getItemId())) {
                // Update quantity of existing item
                int updatedQuantity = existingCartItem.getQuantity() + newCartItem.getQuantity();
                existingCartItem.setQuantity(updatedQuantity);
                // Save and return the updated cart item
                return cartItemRepository.save(existingCartItem);
            }
        }

        // If no existing cart item, set the date and save the new item
        newCartItem.setAddedAt(new Date());
        return cartItemRepository.save(newCartItem);
    }

    // Remove a cart item
    public void removeCartItem(String id) {
        cartItemRepository.deleteById(id);
    }

    // Clear all cart items for a customer
    public void clearCart(String customerId) {
        List<CartItem> cartItems = cartItemRepository.findByCustomerId(customerId);
        cartItemRepository.deleteAll(cartItems);
    }

    // Update the quantity of a cart item
    public CartItem updateCartItemQuantity(String customerId, String itemId, int quantity) {
        // Fetch the cart for the customer
        List<CartItem> cartItems = cartItemRepository.findByCustomerId(customerId);

        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart not found for the given customer ID.");
        }

        // Find the cart item that matches the itemId
        CartItem cartItem = cartItems.stream()
                .filter(item -> item.getItemId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item not found in the cart."));

        if (quantity == 0) {
            // Remove the item if quantity is set to 0
            cartItems.remove(cartItem);
            cartItemRepository.delete(cartItem);
        } else {
            // Update the quantity
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }

        return cartItem;
    }
}
