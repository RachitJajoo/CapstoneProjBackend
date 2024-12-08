package com.Chubb.EcommerceWebSite.model;

import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    private String customerId;
    private String customerName;
    private String itemId;
    private  String review;
    private long rating;

}
