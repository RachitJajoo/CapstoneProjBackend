package com.Chubb.EcommerceWebSite.repo;

import com.Chubb.EcommerceWebSite.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    // Custom query methods can be added here if needed
    List<Order> findByCustomerId(String customerId);
    List<Order> findByVendorId(String vendorId);
}
