package com.Chubb.EcommerceWebSite.model;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "items")
public class Item {

    @Id
    private String id;
    private String name;
    private String description;
    private double original_price;
    private int stockQuantity;
    private String category;
    private String img_url;
    private String vendorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    private List<Review> reviews;

    private String jwtToken;




}
