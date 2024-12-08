package com.Chubb.EcommerceWebSite.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "categories")
public class Category {
    private String id;
    private String name;
    private String description;
    private String parentId;

}
