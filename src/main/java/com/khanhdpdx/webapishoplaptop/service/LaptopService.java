package com.khanhdpdx.webapishoplaptop.service;

import com.khanhdpdx.webapishoplaptop.dto.laptop.LaptopDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LaptopService {
    List<LaptopDTO> findAll();
    LaptopDTO findById(Long id);
    Long save(LaptopDTO laptopDTO);
    Page<LaptopDTO> listByPaged(String keyword, int page, int size, String[] sort);
    Page<LaptopDTO> getLaptopsByCategory(Long categoryId, int page, int size, String[] sort);
}
