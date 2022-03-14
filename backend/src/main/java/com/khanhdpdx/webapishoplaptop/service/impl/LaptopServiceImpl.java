package com.khanhdpdx.webapishoplaptop.service.impl;

import com.khanhdpdx.webapishoplaptop.dto.laptop.CreateLaptopDTO;
import com.khanhdpdx.webapishoplaptop.dto.laptop.LaptopDTO;
import com.khanhdpdx.webapishoplaptop.dto.laptop.LaptopMapper;
import com.khanhdpdx.webapishoplaptop.entity.Category;
import com.khanhdpdx.webapishoplaptop.entity.Laptop;
import com.khanhdpdx.webapishoplaptop.exception.ResourceNotFoundException;
import com.khanhdpdx.webapishoplaptop.repository.LaptopRepository;
import com.khanhdpdx.webapishoplaptop.service.LaptopService;
import com.khanhdpdx.webapishoplaptop.utils.FileUploadUtil;
import com.khanhdpdx.webapishoplaptop.utils.PagingAndSortingUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.khanhdpdx.webapishoplaptop.constant.ApplicationConstant.UPLOAD_DIR;
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
    public LaptopDTO findById(Long laptopId) {
        Laptop laptop = laptopRepository.findByLaptopId(laptopId)
                .orElseThrow(() -> new ResourceNotFoundException("Laptop wasn't found"));

        return LaptopMapper.MAPPER.from(laptop);
    }

    @Override
    public Long save(CreateLaptopDTO createLaptopDTO) {
        Laptop laptop = new Laptop();
        Long id = null;
        try {
            laptop.setCategory(new Category().setCategoryId(createLaptopDTO.getCategoryId()))
                    .setDescription(createLaptopDTO.getDescription())
                    .setName(createLaptopDTO.getName())
                    .setStatus(createLaptopDTO.getStatus())
                    .setPrice(createLaptopDTO.getPrice())
                    .setEnteredDate(new Date());
            id = laptopRepository.save(laptop).getLaptopId();

            //upload file
            StringBuffer pathName = new StringBuffer(UPLOAD_DIR + id + "/");
            FileUploadUtil.uploadFiles(createLaptopDTO.getFiles(), pathName);

            //update link of upload folder
            laptop = laptopRepository.findById(id).get();
            laptop.setLinkImage(pathName.toString());
            laptopRepository.save(laptop);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Page<LaptopDTO> listByPaged(String keyword, int page, int size, String[] sort) {
        if (page <= 0) page = 1;
        if (size <= 0) size = ITEM_PER_PAGE;

        Sort sortIter = PagingAndSortingUtil.processSort(sort);
        Pageable pageable = PageRequest.of(page - 1, size, sortIter);

        Page<Laptop> laptops;
        if (keyword.isEmpty()) {
            laptops = laptopRepository.findAll(pageable);
        } else {
            laptops = laptopRepository.search(keyword, pageable);
        }

        List<LaptopDTO> laptopDTOs = LaptopMapper.MAPPER.fromLaptops(laptops.getContent());
        return new PageImpl<>(laptopDTOs, pageable, laptops.getTotalElements());
    }

    public Page<LaptopDTO> getLaptopsByCategory(Long categoryId, int page, int size, String[] sort) {
        if (page <= 0) page = 1;
        if (size <= 0) size = ITEM_PER_PAGE;

        Sort sortIter = PagingAndSortingUtil.processSort(sort);
        Pageable pageable = PageRequest.of(page - 1, size, sortIter);
        Page<Laptop> laptops = laptopRepository.getLaptopsByCategoryId(categoryId, pageable);

        List<LaptopDTO> laptopDTOs = LaptopMapper.MAPPER.fromLaptops(laptops.getContent());
        return new PageImpl<>(laptopDTOs, pageable, laptops.getTotalElements());
    }

    public List<LaptopDTO> createSlug() {
        List<Laptop> laptops = laptopRepository.findAll();

        for (Laptop laptop : laptops) {
            laptop.setDiscount(0D);
        }
        List<LaptopDTO> laptopDTOS = LaptopMapper.MAPPER.fromLaptops(laptops);

        laptopRepository.saveAll(laptops);

        return laptopDTOS;
    }

    public LaptopDTO findBySlug(String slug) {
        Laptop laptop = laptopRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Product wasn't found"));

        return LaptopMapper.MAPPER.from(laptop);
    }
}
