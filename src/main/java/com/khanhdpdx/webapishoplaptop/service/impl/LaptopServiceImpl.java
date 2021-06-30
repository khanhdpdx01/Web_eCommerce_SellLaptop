package com.khanhdpdx.webapishoplaptop.service.impl;

import com.khanhdpdx.webapishoplaptop.constant.PaginationConstant;
import com.khanhdpdx.webapishoplaptop.dto.laptop.LaptopDTO;
import com.khanhdpdx.webapishoplaptop.dto.laptop.LaptopMapper;
import com.khanhdpdx.webapishoplaptop.entity.Category;
import com.khanhdpdx.webapishoplaptop.entity.Laptop;
import com.khanhdpdx.webapishoplaptop.repository.LaptopRepository;
import com.khanhdpdx.webapishoplaptop.service.LaptopService;
import com.khanhdpdx.webapishoplaptop.utils.PagingAndSortingUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.khanhdpdx.webapishoplaptop.constant.PaginationConstant.ITEM_PER_PAGE;

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

    @Override
    public Long save(LaptopDTO laptopDTO) {
        Laptop laptop = new Laptop();
        laptop.setCategory(new Category().setCategoryId(laptopDTO.getCategory().getCategoryId()))
                .setDescription(laptopDTO.getDescription())
                .setLinkImage(laptopDTO.getLinkImage())
                .setName(laptopDTO.getName())
                .setStatus(laptopDTO.getStatus())
                .setUnitPrice(laptopDTO.getUnitPrice())
                .setEnteredDate(laptopDTO.getEnteredDate());
        return laptopRepository.save(laptop).getLaptopId();
    }

    public Page<LaptopDTO> listByPaged(String keyword, int page, int size, String[] sort) {
        if(page <= 0) page = 1;
        if(size <= 0) size = ITEM_PER_PAGE;

        Sort sortIter = PagingAndSortingUtil.processSort(sort);
        Pageable pageable = PageRequest.of(page -1, size, sortIter);

        Page<Laptop> laptops;
        if(keyword.isEmpty()) {
            laptops = laptopRepository.findAll(pageable);
        } else {
            laptops = laptopRepository.search(keyword, pageable);
        }

        List<LaptopDTO> laptopDTOs = LaptopMapper.MAPPER.fromLaptops(laptops.getContent());
        return new PageImpl<>(laptopDTOs, pageable, laptops.getTotalElements());
    }

}
