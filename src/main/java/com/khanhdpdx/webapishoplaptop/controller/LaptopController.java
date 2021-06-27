package com.khanhdpdx.webapishoplaptop.controller;

import com.khanhdpdx.webapishoplaptop.dto.LaptopDTO;
import com.khanhdpdx.webapishoplaptop.dto.OrderDetailDTO;
import com.khanhdpdx.webapishoplaptop.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
/*@SessionAttributes("cart")*/
public class LaptopController {
    @Autowired
    private LaptopService laptopService;

    @GetMapping
    public String list(/*@ModelAttribute("cart") List<ShoppingCartDTO> cart,*/
            Model model,
            HttpServletRequest request) {
        List<OrderDetailDTO> cart = getShoppingCart(request);
        model.addAttribute("products", laptopService.findAll());
        model.addAttribute("cart", cart);
        return "client/product/list";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public LaptopDTO findOne(@PathVariable Long id) {
        return laptopService.findById(id);
    }

    @GetMapping("/cart")
    @ResponseBody
    public List<OrderDetailDTO> getCart(@ModelAttribute("cart") List<OrderDetailDTO> cart) {
        return cart;
    }

    @GetMapping("/cart/{laptop-id}")
    @ResponseBody
    public List<OrderDetailDTO> addToCart(/*@ModelAttribute("cart") List<ShoppingCartDTO> cart,*/
                                           @PathVariable("laptop-id") Long id,
                                           HttpServletRequest request) {
        List<OrderDetailDTO> cart = getShoppingCart(request);
        boolean existedLaptop = false;
        for (OrderDetailDTO item : cart) {
            if (item.getLaptop().getLaptopId() == id) {
                existedLaptop = true;
                item.setQuantity(item.getQuantity() + 1);
                item.setUnitPrice(item.getUnitPrice() + item.getLaptop().getUnitPrice());
            }
        }

        // laptop not exist
        if (!existedLaptop) {
            LaptopDTO newItem = laptopService.findById(id);
            cart.add(new OrderDetailDTO(
                    newItem,
                    1,
                    newItem.getUnitPrice(),
                    0
            ));
        }
        return cart;
    }

    /*@ModelAttribute("cart")
    public List<ShoppingCartDTO> create(HttpServletRequest request) {
        // fix error: Cannot create a session after the response has been committed
        // followed by https://github.com/spring-projects/spring-framework/issues/17475
        request.getSession(true);
        return new ArrayList<>();
    }*/

    @PostMapping("/cart/{laptop-id}")
    @ResponseBody
    public List<OrderDetailDTO> updateCart(/*@ModelAttribute("cart") List<ShoppingCartDTO> cart,*/
                                            @PathVariable("laptop-id") Long id, Integer quantity,
                                            HttpServletRequest request) {
        List<OrderDetailDTO> cart = getShoppingCart(request);
        if (quantity > 0) {
            for (OrderDetailDTO item : cart) {
                if (item.getLaptop().getLaptopId() == id) {
                    item.setQuantity(quantity);
                    item.setUnitPrice(item.getLaptop().getUnitPrice() * quantity);
                    break;
                }
            }
        }
        return cart;
    }

    @DeleteMapping("/cart/{laptop-id}")
    @ResponseBody
    public List<OrderDetailDTO> deleteCart(/*@ModelAttribute("cart") List<ShoppingCartDTO> cart,*/
                                            @PathVariable("laptop-id") Long id,
                                            HttpServletRequest request) {
        List<OrderDetailDTO> cart = getShoppingCart(request);
        int size = cart.size();
        for (int i = 0; i < size; ++i) {
            if (cart.get(i).getLaptop().getLaptopId() == id) {
                cart.remove(i);
                break;
            }
        }
        return cart;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<LaptopDTO> getAll() {
        return laptopService.findAll();
    }

    private List<OrderDetailDTO> getShoppingCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<OrderDetailDTO> cart = (List<OrderDetailDTO>)session.getAttribute("cart");
        if(cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    // cal sum money
    // payment -> order -> order details
}
