package com.khanhdpdx.webapishoplaptop.controller;

import com.khanhdpdx.webapishoplaptop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
}
