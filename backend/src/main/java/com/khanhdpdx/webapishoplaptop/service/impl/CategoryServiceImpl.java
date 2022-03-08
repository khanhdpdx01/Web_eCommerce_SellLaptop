package com.khanhdpdx.webapishoplaptop.service.impl;

import com.khanhdpdx.webapishoplaptop.dto.category.CategoryDTO;
import com.khanhdpdx.webapishoplaptop.dto.category.CategoryMapper;
import com.khanhdpdx.webapishoplaptop.repository.CategoryRepository;
import com.khanhdpdx.webapishoplaptop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> findAll() {
        return CategoryMapper.MAPPER.fromCategories(categoryRepository.findAll());
    }
}
