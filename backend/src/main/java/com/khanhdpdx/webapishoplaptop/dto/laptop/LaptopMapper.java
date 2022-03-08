package com.khanhdpdx.webapishoplaptop.dto.laptop;

import com.khanhdpdx.webapishoplaptop.entity.Laptop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LaptopMapper {
    LaptopMapper MAPPER = Mappers.getMapper(LaptopMapper.class);

    LaptopDTO from(Laptop laptop);

    List<LaptopDTO> fromLaptops(List<Laptop> laptops);
}
