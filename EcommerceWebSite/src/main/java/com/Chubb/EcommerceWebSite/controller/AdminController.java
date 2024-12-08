package com.Chubb.EcommerceWebSite.controller;


import com.Chubb.EcommerceWebSite.config.JwtGenerator;
import com.Chubb.EcommerceWebSite.config.RBACUtil;
import com.Chubb.EcommerceWebSite.model.*;
import com.Chubb.EcommerceWebSite.repo.AdminRepository;
import com.Chubb.EcommerceWebSite.repo.CategoryRepository;
import com.Chubb.EcommerceWebSite.service.CustomerService;
import com.Chubb.EcommerceWebSite.service.ItemService;
import com.Chubb.EcommerceWebSite.service.OrderService;
import com.Chubb.EcommerceWebSite.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admins")
public class AdminController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private RBACUtil rbacUtil;


    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(@RequestBody Admin admin) throws Exception {

        Admin loginAdmin = adminRepository.findByEmail(admin.getEmail());

//        System.out.println("Found Admin: " + loginAdmin);
        String currentPassword = admin.getPassword();
        if (loginAdmin.getPassword().equals(currentPassword)) {
            String jwtToken = jwtGenerator.generateToken(loginAdmin.getId(), "ADMIN");
            loginAdmin.setJwtToken(jwtToken);
//            System.out.println(loginAdmin.getJwtToken());
            loginAdmin.setPassword(null);
            return ResponseEntity.ok(loginAdmin);
        }

        throw new Exception("No Such Admin Exists");
    }

    @PostMapping("/category")
    public ResponseEntity<Category> addCategory(@RequestHeader("Authorization") String token ,@RequestBody Category category) {
        rbacUtil.verifyAccess(token.replace("Bearer ", ""), "ADMIN");
        if(category.getParentId().equals(""))category.setParentId(null);
        Category newCategory = categoryRepository.save(category);
        return ResponseEntity.ok(newCategory);
    }

    @PutMapping("/vendor/{vendorId}")
    public ResponseEntity<Vendor> updateVendor(@RequestHeader("Authorization") String token ,@PathVariable String vendorId, @RequestBody Vendor vendor) {
        rbacUtil.verifyAccess(token.replace("Bearer ", ""), "ADMIN");
        return ResponseEntity.ok(vendorService.setVendorActiveStatus(vendorId, vendor));
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(@RequestHeader("Authorization") String token ) {
        rbacUtil.verifyAccess(token.replace("Bearer ", ""), "ADMIN");
        return categoryRepository.findAll();
    }

    @GetMapping("/items")
    public List<Item> getAllItems(@RequestHeader("Authorization") String token ) {
        rbacUtil.verifyAccess(token.replace("Bearer ", ""), "ADMIN");
        return itemService.getAllItems();
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(@RequestHeader("Authorization") String token ) {
        rbacUtil.verifyAccess(token.replace("Bearer ", ""), "ADMIN");
        return orderService.getAllOrders();
    }


    @GetMapping("/vendors")
    public List<Vendor> getAllVendors(@RequestHeader("Authorization") String token) {
        rbacUtil.verifyAccess(token.replace("Bearer ", ""), "ADMIN");
        return vendorService.getAllVendors();
    }


    @GetMapping("/customers")
    public List<Customer> getAllCustomers(@RequestHeader("Authorization") String token) {
        rbacUtil.verifyAccess(token.replace("Bearer ", ""), "ADMIN");
        return customerService.getAllCustomers();
    }
}
