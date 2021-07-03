package com.khanhdpdx.webapishoplaptop.dto.category;

import com.khanhdpdx.webapishoplaptop.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO from(Category category);

    List<CategoryDTO> fromCategories(List<Category> categories);
}
