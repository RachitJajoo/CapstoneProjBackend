package com.Chubb.EcommerceWebSite.repo;

import com.Chubb.EcommerceWebSite.model.CartItem;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends MongoRepository<CartItem, String> {
    List<CartItem> findByCustomerId(String customerId);
    List<CartItem> findByCustomerIdAndItemId(String customerId, String itemId);

}
