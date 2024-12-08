package com.Chubb.EcommerceWebSite.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;


@Data
@Document(collection = "cartItems")
public class CartItem {

    @Id
    private String id;
    private String customerId; // References the User ID
    private String itemId;     // Single Item ID (instead of List of Item IDs)
    private Integer quantity;  // Corresponding quantity (instead of List of Quantities)
    private Date addedAt;
    private String jwtToken;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @DBRef
    private Customer customer;

    @DBRef
    private Item item;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    // Constructors, Getters, and Setters
    public CartItem() {
        this.addedAt = new Date();
    }

    public CartItem(String customerId, String itemId, Integer quantity) {
        this.customerId = customerId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.addedAt = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", quantity=" + quantity +
                ", addedAt=" + addedAt +
                ", customer=" + customer +
                ", item=" + item +
                '}';
    }
}
