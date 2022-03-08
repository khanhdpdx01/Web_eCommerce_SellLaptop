package com.khanhdpdx.webapishoplaptop.service;

import com.khanhdpdx.webapishoplaptop.dto.category.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
}
