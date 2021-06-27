package com.khanhdpdx.webapishoplaptop.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class OrderDTO {
    private Date orderedDate;

    private String shipAddress;

    private Float freightCost;

    private Integer status;

    private String username;

    private Long shipperId;

    private Long paymentId;

    private Long userId;
}
