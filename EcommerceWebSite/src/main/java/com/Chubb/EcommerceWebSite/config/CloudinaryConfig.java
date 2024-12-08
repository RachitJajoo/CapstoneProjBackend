package com.Chubb.EcommerceWebSite.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary getCloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dsl586drq",
                "api_key", "133395713262916",
                "api_secret", "gP6dlKHbFAjz_1twYxJKOEAwGMQ",
                "secure", true));
    }
}
