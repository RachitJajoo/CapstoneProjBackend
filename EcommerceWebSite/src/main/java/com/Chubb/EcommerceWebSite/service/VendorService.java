package com.Chubb.EcommerceWebSite.service;


//import com.Chubb.EcommerceWebSite.model.Vendor;

import com.Chubb.EcommerceWebSite.model.Vendor;
import com.Chubb.EcommerceWebSite.repo.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor getVendorById(String id) {
        Optional<Vendor> vendor = vendorRepository.findById(id);
        return vendor.orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));
    }


    public Vendor getVendorByEmail(String email) {
        Vendor vendor = vendorRepository.findByEmail(email);
        if (vendor == null) {
            throw new RuntimeException("Vendor not found with email: " + email);
        }
        return vendor;
    }


    public Vendor getVendorByUsername(String username) {
        return vendorRepository.findByUsername(username);
    }

    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }


    public void updateVendorReviews(String vendorId, int reviews) {
        Vendor vendor = vendorRepository.findById(vendorId).orElse(null);
        if (vendor != null) {
            vendor.setReviews(reviews);
            vendorRepository.save(vendor);
        }
    }

    public Vendor setVendorActiveStatus(String vendorId, Vendor updatedVendor) {
        Vendor existingVendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + vendorId));


        existingVendor.setIsActive(updatedVendor.getIsActive());
        return vendorRepository.save(existingVendor);
    }

}
