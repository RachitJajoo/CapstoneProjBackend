package com.Chubb.EcommerceWebSite.model;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "admin")
public class Admin {
    private String id;
    private String email;
    private String username;
    private String password;
    @Transient
    private String jwtToken;
}
