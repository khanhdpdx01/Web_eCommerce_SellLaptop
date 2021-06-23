package com.khanhdpdx.webapishoplaptop.service;

import com.khanhdpdx.webapishoplaptop.dto.LaptopDTO;
import com.khanhdpdx.webapishoplaptop.entity.Laptop;

import java.util.List;
import java.util.Optional;

public interface LaptopService {
    List<LaptopDTO> findAll();
    LaptopDTO findById(Long id);
}
