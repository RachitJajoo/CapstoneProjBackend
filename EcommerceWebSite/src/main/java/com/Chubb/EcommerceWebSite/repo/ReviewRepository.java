package com.Chubb.EcommerceWebSite.repo;

import com.Chubb.EcommerceWebSite.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review , String> {
    List<Review> findByCustomerId(String customerId);

    List<Review> findByItemId(String itemId);
}
