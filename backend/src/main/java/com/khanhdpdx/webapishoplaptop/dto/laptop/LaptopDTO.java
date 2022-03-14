package com.khanhdpdx.webapishoplaptop.dto.laptop;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.khanhdpdx.webapishoplaptop.dto.category.CategoryDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LaptopDTO {
    private Long laptopId;

    private String name;

    private Float price;

    private String linkImage;

    private String description;

    private Integer discount;

    private String slug;

    private Date enteredDate;

    private Integer status;

    private CategoryDTO category;
}
