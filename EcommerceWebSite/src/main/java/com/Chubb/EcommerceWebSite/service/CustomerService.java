package com.Chubb.EcommerceWebSite.service;

import com.Chubb.EcommerceWebSite.model.Customer;
import com.Chubb.EcommerceWebSite.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    public Customer getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new RuntimeException("Customer not found with email: " + email);
        }
        return customer;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(String id, Customer customer) {
        Customer existingCustomer = getCustomerById(id);
        existingCustomer.setUsername(customer.getUsername());
        existingCustomer.setPassword(customer.getPassword());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setIsActive(customer.getIsActive());
        existingCustomer.setShippingAddress(customer.getShippingAddress());
        existingCustomer.setOrderHistory(customer.getOrderHistory());
        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(String id) {
        Customer existingCustomer = getCustomerById(id);
        customerRepository.delete(existingCustomer);
    }

}