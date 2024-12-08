package com.Chubb.EcommerceWebSite.service;


import com.Chubb.EcommerceWebSite.model.Customer;
import com.Chubb.EcommerceWebSite.model.Item;
import com.Chubb.EcommerceWebSite.model.Order;
import com.Chubb.EcommerceWebSite.model.Vendor;
import com.Chubb.EcommerceWebSite.repo.CustomerRepository;
import com.Chubb.EcommerceWebSite.repo.ItemRepository;
import com.Chubb.EcommerceWebSite.repo.OrderRepository;
import com.Chubb.EcommerceWebSite.repo.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ItemRepository itemRepository;


    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }


    public List<Order> getOrdersByVendorId(String vendorId){

        List<Order> orders = orderRepository.findByVendorId(vendorId);

        // Populate Customer details for each order
        for (Order order : orders) {
            Optional<Item> item = itemRepository.findById(order.getItemId());
            item.ifPresent(order::setItem);
            Optional<Customer> customer = customerRepository.findById(order.getCustomerId());
            customer.ifPresent(order::setCustomer);
        }
        return orders;
    }

    public List<Order> getOrdersByCustomerId(String customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);

        // Populate Vendor details for each order
        for (Order order : orders) {
            Optional<Item> item = itemRepository.findById(order.getItemId());
            item.ifPresent(order::setItem);
            Optional<Vendor> vendor = vendorRepository.findById(order.getVendorId());
            vendor.ifPresent(order::setVendor);
        }
        return orders;
    }

}
