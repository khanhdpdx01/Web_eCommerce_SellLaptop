package com.khanhdpdx.webapishoplaptop.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private Date orderedDate;

    private String shipAddress;

    private Float freightCost;

    private Integer status;

    private String username;

    private String companyName;

    private String paymentName;
}
