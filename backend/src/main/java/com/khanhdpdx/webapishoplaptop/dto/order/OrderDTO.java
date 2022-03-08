package com.khanhdpdx.webapishoplaptop.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class OrderDTO {
    private Date orderedDate;

    @NotBlank(message = "Shipaddress is mandatory")
    private String shipAddress;

    @Min(value = 0, message = "Value is not valid")
    private Float freightCost;

    private Integer status;

    @NotBlank(message = "name is mandatory")
    private String username;

    private Long shipperId;

    private Long paymentId;

    private Long userId;
}
