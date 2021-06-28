package com.khanhdpdx.webapishoplaptop.controller;

import com.khanhdpdx.webapishoplaptop.dto.OrderDetailDTO;
import com.khanhdpdx.webapishoplaptop.dto.ShoppingCartDTO;
import com.khanhdpdx.webapishoplaptop.dto.order.OrderDTO;
import com.khanhdpdx.webapishoplaptop.dto.payment.PaymentDTO;
import com.khanhdpdx.webapishoplaptop.dto.shipper.ShipperDTO;
import com.khanhdpdx.webapishoplaptop.entity.Payment;
import com.khanhdpdx.webapishoplaptop.security.UserDetailsServiceImpl;
import com.khanhdpdx.webapishoplaptop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @Autowired
    private ShoppingCartDTO shoppingCartDTO;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/list-orders")
    @ResponseBody
    public ResponseEntity<List<OrderDTO>> getListOrder() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/order")
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
    public String save(Model model,
                       @ModelAttribute("order") @Valid OrderDTO orderDTO,
                       BindingResult result,
                       Principal principal,
                       HttpServletRequest request,
                       RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            model.addAttribute("shipper", shipperService.findAll());
            model.addAttribute("payment", paymentService.findAll());
            return "client/order/order";
        }
        // get id user
        orderDTO.setUserId(userService.findFirstByUsername(principal.getName()).getUserId());

        // save order and get order_id
        Long orderId = orderService.createOrder(orderDTO);

        // get shopping cart from session
        List<OrderDetailDTO> orderDetailDTOs = shoppingCartDTO.getShoppingCart(request);
        //save order_detail
        for(OrderDetailDTO orderDetailDTO : orderDetailDTOs) {
            orderDetailService.save(orderDetailDTO, orderId);
        }
        //delete shopping cart
        orderDetailDTOs.clear();

        // notify message
        redirectAttributes.addFlashAttribute("message", "Đặt hàng thành công");
        return "redirect:/product";
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
