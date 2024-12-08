package com.Chubb.EcommerceWebSite.repo;



import com.Chubb.EcommerceWebSite.model.Item;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    // Additional queries can be added here if needed
    List<Item> findByVendorId(String vendorId);
}