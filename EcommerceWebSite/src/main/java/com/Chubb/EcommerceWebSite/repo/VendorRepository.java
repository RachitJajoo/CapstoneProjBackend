package com.Chubb.EcommerceWebSite.repo;


import com.Chubb.EcommerceWebSite.model.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends MongoRepository<Vendor, String> {

    Vendor findByUsername(String username);
    Vendor findByEmail(String email);
}