package com.khanhdpdx.webapishoplaptop.dto;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
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
@Component
public class ShoppingCartDTO {
    private List<OrderDetailDTO> orderDetailDTOs;
    private String JSESSIONID;


    public List<OrderDetailDTO> getShoppingCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<OrderDetailDTO> cart = (List<OrderDetailDTO>)session.getAttribute("cart");
        if(cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    /*
    khi đăng ký, tạo ngay session_id cho giỏ hàng
    khi login, truy vấn và trả về session_id và access_token
    + dua vao
    * */

}
