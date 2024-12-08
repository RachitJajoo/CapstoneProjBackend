package com.Chubb.EcommerceWebSite.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "vendors")
public class Vendor {

    @Id
    private String id;  // MongoDB _id
    private String username;
    private String password;
    private long phoneNumber;
    private String email;
    private String isActive;
    private String role; // vendor, customer, admin
    private String storeName;
    private List<String> productList; // List of product IDs
    private String jwtToken;
    private int reviews; // Average customer review score


}