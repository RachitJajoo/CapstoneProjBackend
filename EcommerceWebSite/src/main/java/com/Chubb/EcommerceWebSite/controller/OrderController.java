package com.Chubb.EcommerceWebSite.controller;


import com.Chubb.EcommerceWebSite.config.RBACUtil;
import com.Chubb.EcommerceWebSite.model.CartItem;
import com.Chubb.EcommerceWebSite.model.Order;
import com.Chubb.EcommerceWebSite.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RBACUtil rbacUtil;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId( @PathVariable String customerId) {
//        rbacUtil.verifyAccess(token.replace("Bearer ", ""),"CUSTOMER");
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }


    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<Order>> getOrdersByVendorId( @PathVariable String vendorId) {
//        rbacUtil.verifyAccess(token.replace("Bearer ", ""),"VENDOR");
        List<Order> orders = orderService.getOrdersByVendorId(vendorId);
        System.out.println("ORDERS" + orders);
        return ResponseEntity.ok(orders);
    }


    @PostMapping("/add")
    public void createOrder(@RequestBody CartItem orderList){

    }
}
