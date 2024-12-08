package com.Chubb.EcommerceWebSite.model;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
    private String username;
    private String password;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    private String jwtToken;
    public Customer() {
    }

    private Long phoneNumber;
    private String email;
    private Boolean isActive;
    private String shippingAddress;
    private List<Integer> orderHistory;

    public Customer(String id, String username, String password, Long phoneNumber, String email, Boolean isActive, String shippingAddress, List<Integer> orderHistory) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isActive = isActive;
        this.shippingAddress = shippingAddress;
        this.orderHistory = orderHistory;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Integer> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Integer> orderHistory) {
        this.orderHistory = orderHistory;
    }
}

