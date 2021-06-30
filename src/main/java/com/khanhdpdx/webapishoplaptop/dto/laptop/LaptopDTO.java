package com.khanhdpdx.webapishoplaptop.dto.laptop;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.khanhdpdx.webapishoplaptop.dto.CategoryDTO;
import com.khanhdpdx.webapishoplaptop.entity.Category;
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

    private Float unitPrice;

    private String linkImage;

    private String description;

    private Date enteredDate;

    private Integer status;

    private CategoryDTO category;
}
