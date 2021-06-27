package com.khanhdpdx.webapishoplaptop.dto;

import lombok.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShoppingCartDTO {
    private List<OrderDetailDTO> orderDetailDTOs;
    private String JSESSIONID;

    /*
    khi đăng ký, tạo ngay session_id cho giỏ hàng
    khi login, truy vấn và trả về session_id và access_token
    + dua vao
    * */

}
