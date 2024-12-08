package com.Chubb.EcommerceWebSite.service;

import com.Chubb.EcommerceWebSite.config.CloudinaryConfig;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;
    public Map upload(MultipartFile file)  {
        Map data = null;
        try {
            data = cloudinary.uploader().upload(file.getBytes() , Map.of());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
