package com.Chubb.EcommerceWebSite.controller;

import com.Chubb.EcommerceWebSite.config.RBACUtil;
import com.Chubb.EcommerceWebSite.model.CartItem;
import com.Chubb.EcommerceWebSite.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private RBACUtil rbacUtil;

    // Get cart items for a specific customer
    @GetMapping("/{customerId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable String customerId) {

//        rbacUtil.verifyAccess(token.replace("Bearer ", ""),"CUSTOMER");
        List<CartItem> cartItems = cartItemService.getCartItemsByCustomerId(customerId);
//        for(CartItem c : cartItems){
//            System.out.println(c.toString());
//        }
        return ResponseEntity.ok(cartItems);  // The populated fields should be included automatically.
    }

//     Add a new item to the cart
    @PostMapping("/add")
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItem) {
//        rbacUtil.verifyAccess(token.replace("Bearer ", ""),"CUSTOMER");
//        System.out.println("Adding cart item: " + cartItem);
        CartItem addedItem = cartItemService.addCartItem(cartItem);
//        System.out.println(addedItem.toString());
        return ResponseEntity.status(201).body(addedItem); // Return 201 Created
    }



    // Update the quantity of an existing item in the cart
    @PutMapping("/{customerId}/update/{itemId}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable String customerId, @PathVariable String itemId, @RequestBody int quantity ) {
//        rbacUtil.verifyAccess(token.replace("Bearer ", ""),"CUSTOMER");
        if (quantity < 0) {
            return ResponseEntity.badRequest().build(); // Handle invalid quantity
        }
        CartItem updatedItem = cartItemService.updateCartItemQuantity(customerId, itemId, quantity);
        return ResponseEntity.ok(updatedItem);
    }

    // Remove an item from the cart
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeCartItem(@PathVariable String id) {
//        rbacUtil.verifyAccess(token.replace("Bearer ", ""),"CUSTOMER");

        cartItemService.removeCartItem(id);
        return ResponseEntity.noContent().build();
    }

    // Clear all items from the customer's cart
    @DeleteMapping("/clear/{customerId}")
//    public ResponseEntity<Void> clearCart(@RequestHeader("Authorization") String token ,@PathVariable String customerId) {
    public ResponseEntity<Void> clearCart(@PathVariable String customerId) {
//        rbacUtil.verifyAccess(token.replace("Bearer ", ""),"CUSTOMER");
        cartItemService.clearCart(customerId);
        return ResponseEntity.noContent().build();
    }
}
