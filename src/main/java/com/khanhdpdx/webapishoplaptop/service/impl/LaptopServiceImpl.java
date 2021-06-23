package com.khanhdpdx.webapishoplaptop.service.impl;

import com.khanhdpdx.webapishoplaptop.dto.LaptopDTO;
import com.khanhdpdx.webapishoplaptop.entity.Laptop;
import com.khanhdpdx.webapishoplaptop.repository.LaptopRepository;
import com.khanhdpdx.webapishoplaptop.service.LaptopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {
    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LaptopDTO> findAll() {
        List<LaptopDTO> laptopDTOs = laptopRepository.findAll()
                .stream()
                .map(laptop -> modelMapper.map(laptop, LaptopDTO.class))
                .collect(Collectors.toList());
        return laptopDTOs;
    }

    @Override
    public LaptopDTO findById(Long id) {
        Optional<Laptop> laptop = laptopRepository.findById(id);
        if(laptop.isPresent()) return modelMapper.map(laptop.get(), LaptopDTO.class);
        return null;
    }

}
