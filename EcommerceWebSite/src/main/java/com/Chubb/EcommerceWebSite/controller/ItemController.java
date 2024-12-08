package com.Chubb.EcommerceWebSite.controller;


import com.Chubb.EcommerceWebSite.config.RBACUtil;
import com.Chubb.EcommerceWebSite.model.Item;
import com.Chubb.EcommerceWebSite.model.Vendor;
import com.Chubb.EcommerceWebSite.repo.VendorRepository;
import com.Chubb.EcommerceWebSite.service.CloudinaryService;
import com.Chubb.EcommerceWebSite.service.ItemService;
import com.Chubb.EcommerceWebSite.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private RBACUtil rbacUtil;

    @Autowired
    private VendorRepository vendorRepository;

    @GetMapping("/getItems")
    public List<Item> getAllItems() {

        return itemService.getAllItems();
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable String itemId) {
        return ResponseEntity.ok(itemService.getItemById(itemId));
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<Item>> getItemByVendorId(@PathVariable String vendorId){
        return  ResponseEntity.ok(itemService.getItemByVendorId(vendorId));
    }



    @PostMapping("/add")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        String v_id = item.getVendorId();
        Vendor currentVendor = vendorService.getVendorById(v_id);
        ResponseEntity<Item> currentSavedItem = ResponseEntity.ok(itemService.saveItem(item));
        String item_id = currentSavedItem.getBody().getId();
        currentVendor.getProductList().add(item_id);
        vendorRepository.save(currentVendor);
        return currentSavedItem;
    }



    @PostMapping("/add/image")
    public ResponseEntity<Map> addImage(@RequestParam("image") MultipartFile file) {
        Map data = this.cloudinaryService.upload(file);
        System.out.println(data);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable String id, @RequestBody Item updatedItem) {

        return ResponseEntity.ok(itemService.updateItem(id, updatedItem));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
