package com.Chubb.EcommerceWebSite.repo;

import com.Chubb.EcommerceWebSite.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin , String> {
    Admin findByEmail(String email);
}
