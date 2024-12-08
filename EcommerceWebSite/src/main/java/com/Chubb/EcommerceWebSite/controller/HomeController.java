package com.Chubb.EcommerceWebSite.controller;

import com.Chubb.EcommerceWebSite.model.Customer;
import com.Chubb.EcommerceWebSite.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(originPatterns = "http://localhost:4200")
@RestController
public class HomeController {


    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public List<Customer> home(){

        return customerService.getAllCustomers();
    }
}
