package com.khanhdpdx.webapishoplaptop.dto.pagination;

import com.khanhdpdx.webapishoplaptop.dto.laptop.LaptopDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface PaginationMapper {
    PaginationMapper MAPPER = Mappers.getMapper(PaginationMapper.class);

    default PaginationResponse<LaptopDTO> from(Page<LaptopDTO> page) {
        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setData(page.getContent());
        paginationResponse.setCurrentPage(page.getNumber() + 1);
        paginationResponse.setTotalItems(page.getSize());
        paginationResponse.setTotalPages(page.getTotalPages());
        return paginationResponse;
    }
}
