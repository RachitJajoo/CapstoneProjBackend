package com.Chubb.EcommerceWebSite.controller;

//import com.Chubb.EcommerceWebSite.model.Vendor;
import com.Chubb.EcommerceWebSite.config.JwtGenerator;
import com.Chubb.EcommerceWebSite.model.Category;
import com.Chubb.EcommerceWebSite.model.Vendor;
import com.Chubb.EcommerceWebSite.repo.CategoryRepository;
import com.Chubb.EcommerceWebSite.service.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private JwtGenerator jwtGenerator;

    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<Vendor> getVendorById(@PathVariable String id) {
        Vendor vendor = vendorService.getVendorById(id);
        System.out.println(vendor.toString());
        return ResponseEntity.ok(vendor);
    }



    @PostMapping("/register")
    public ResponseEntity<Vendor> addVendor(@RequestBody Vendor vendor) {
        Vendor createdVendor = vendorService.createVendor(vendor);
        return ResponseEntity.ok(createdVendor);
    }


    @PostMapping("/login")
    public ResponseEntity<Vendor> loginVendor(@RequestBody Vendor vendor) throws Exception {
        System.out.println("At Route /vendor/login");
        Vendor loginVendor = vendorService.getVendorByEmail(vendor.getEmail());
        String currentPassword = vendor.getPassword();
        if(loginVendor.getPassword().equals(currentPassword)){
            String jwtToken = jwtGenerator.generateToken(vendor.getId() , "VENDOR");
            loginVendor.setPassword(null);
            loginVendor.setJwtToken(jwtToken);
            return ResponseEntity.ok(loginVendor);
        }

        throw new Exception("No Such User Found");
    }

    @Autowired
    private  CategoryRepository categoryRepository;


    @GetMapping("categories/get")
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }


    @PatchMapping("/{vendorId}/reviews")
    public void updateVendorReviews(@PathVariable String vendorId, @RequestParam int reviews) {
        vendorService.updateVendorReviews(vendorId, reviews);
    }
}
