package com.khanhdpdx.webapishoplaptop.controller;

import com.khanhdpdx.webapishoplaptop.dto.order.OrderDTO;
import com.khanhdpdx.webapishoplaptop.dto.payment.PaymentDTO;
import com.khanhdpdx.webapishoplaptop.dto.shipper.ShipperDTO;
import com.khanhdpdx.webapishoplaptop.entity.Payment;
import com.khanhdpdx.webapishoplaptop.security.UserDetailsServiceImpl;
import com.khanhdpdx.webapishoplaptop.service.OrderService;
import com.khanhdpdx.webapishoplaptop.service.PaymentService;
import com.khanhdpdx.webapishoplaptop.service.ShipperService;
import com.khanhdpdx.webapishoplaptop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ShipperService shipperService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @GetMapping("/list-orders")
    @ResponseBody
    public ResponseEntity<List<OrderDTO>> getListOrder() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("order")
    public String displayFormOrder(Model model) {
        model.addAttribute("order", new OrderDTO());
        model.addAttribute("shipper", shipperService.findAll());
        model.addAttribute("payment", paymentService.findAll());
        return "client/order/order";
    }

    @GetMapping("/shipper")
    @ResponseBody
    public List<ShipperDTO> getShipper() {
        return shipperService.findAll();
    }

    @PostMapping("/order")
    @ResponseBody
    public OrderDTO save(@ModelAttribute OrderDTO orderDTO, Principal principal) {
        orderDTO.setUserId(userService.findFirstByUsername(principal.getName()).getUserId());
        System.out.println(orderDTO.toString());
        return orderService.createOrder(orderDTO);
    }

    @GetMapping("/payment")
    @ResponseBody
    public List<PaymentDTO> getPayment() {
        return paymentService.findAll();
    }

    @GetMapping("/order/add")
    public HttpStatus add() {
        return HttpStatus.OK;
    }

}
