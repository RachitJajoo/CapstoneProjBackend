package com.Chubb.EcommerceWebSite.repo;

import com.Chubb.EcommerceWebSite.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    // Add custom query methods if needed
    Customer findByUsername(String username);
    Customer findByEmail(String email);
}
