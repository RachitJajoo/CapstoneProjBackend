package com.Chubb.EcommerceWebSite.service;


import com.Chubb.EcommerceWebSite.model.Customer;
import com.Chubb.EcommerceWebSite.model.Item;
import com.Chubb.EcommerceWebSite.model.Review;
import com.Chubb.EcommerceWebSite.repo.CustomerRepository;
import com.Chubb.EcommerceWebSite.repo.ItemRepository;
import com.Chubb.EcommerceWebSite.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(String itemId) {
        // Fetch the item by its ID
        System.out.println(itemId);
        Optional<Item> itemOpt = itemRepository.findById(itemId);
        if (itemOpt.isPresent()) {
            Item item = itemOpt.get();
            List<Review> reviews = reviewService.getReviewsByItemId(itemId);
            item.setReviews(reviews);
            return item;
        }

        return null; // or throw an exception if you prefer
    }


    public List<Item> getItemByVendorId(String vendorId) {
        return itemRepository.findByVendorId(vendorId);
    }


    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(String id, Item updatedItem) {
        Item existingItem = getItemById(id);

        existingItem.setName(updatedItem.getName());
        existingItem.setDescription(updatedItem.getDescription());
        existingItem.setOriginal_price(updatedItem.getOriginal_price());
        existingItem.setStockQuantity(updatedItem.getStockQuantity());
        existingItem.setCategory(updatedItem.getCategory());
        existingItem.setImg_url(updatedItem.getImg_url());
        existingItem.setVendorId(updatedItem.getVendorId());
        existingItem.setUpdatedAt(LocalDateTime.now());

        return itemRepository.save(existingItem);
    }

    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }
}
