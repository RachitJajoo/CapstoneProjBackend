package com.Chubb.EcommerceWebSite.controller;

import com.Chubb.EcommerceWebSite.config.JwtGenerator;
import com.Chubb.EcommerceWebSite.config.RBACUtil;
import com.Chubb.EcommerceWebSite.model.Customer;
import com.Chubb.EcommerceWebSite.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private RBACUtil rbacUtil;


    @GetMapping
    public List<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(createdCustomer);
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> loginCustomer(@RequestBody Customer customer) throws Exception {
        System.out.println("At Route /customer/login");
        Customer loginCustomer = customerService.getCustomerByEmail(customer.getEmail());
        String currentPassword = customer.getPassword();
        if(loginCustomer.getPassword().equals(currentPassword)){
            String jwtToken = jwtGenerator.generateToken(customer.getId() , "CUSTOMER");
            loginCustomer.setPassword(null);
            loginCustomer.setJwtToken(jwtToken);
            return ResponseEntity.ok(loginCustomer);
        }

        throw new Exception("No Such User Found");
    }

}