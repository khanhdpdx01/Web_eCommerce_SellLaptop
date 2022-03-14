package com.khanhdpdx.webapishoplaptop.dto.laptop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CreateLaptopDTO {
    @NotNull
    private String name;
    @Min(value = 0)
    private Float price;
    @NotNull
    private String description;
    @Min(value = 1L)
    private Integer status;
    @Min(value = 1L)
    private Long categoryId;

    public List<MultipartFile> files;
}
