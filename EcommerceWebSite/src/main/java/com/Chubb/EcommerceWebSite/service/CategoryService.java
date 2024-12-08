package com.Chubb.EcommerceWebSite.service;


import com.Chubb.EcommerceWebSite.model.Category;
import com.Chubb.EcommerceWebSite.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }


//    public

}
