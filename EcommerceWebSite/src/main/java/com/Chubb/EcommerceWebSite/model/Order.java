package com.Chubb.EcommerceWebSite.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private String customerId;
    private String vendorId;
    private String itemId;
    private Double totalAmount;
    private Date orderDate;
    private Date estimatedDeliveryDate;
    private String orderStatus;
    @Transient
    private Customer customer;
    @Transient
    private Vendor vendor;
    @Transient
    public Item item;

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", vendorId='" + vendorId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", totalAmount=" + totalAmount +
                ", orderDate=" + orderDate +
                ", estimatedDeliveryDate=" + estimatedDeliveryDate +

                '}';
    }

}
