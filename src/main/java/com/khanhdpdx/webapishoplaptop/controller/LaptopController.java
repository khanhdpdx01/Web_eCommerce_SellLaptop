package com.khanhdpdx.webapishoplaptop.controller;

import com.khanhdpdx.webapishoplaptop.dto.OrderDetailDTO;
import com.khanhdpdx.webapishoplaptop.dto.ShoppingCartDTO;
import com.khanhdpdx.webapishoplaptop.dto.laptop.LaptopDTO;
import com.khanhdpdx.webapishoplaptop.service.CategoryService;
import com.khanhdpdx.webapishoplaptop.service.LaptopService;
import com.khanhdpdx.webapishoplaptop.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.khanhdpdx.webapishoplaptop.constant.ApplicationConstant.UPLOAD_DIR;
import static com.khanhdpdx.webapishoplaptop.constant.PaginationConstant.ITEM_PER_PAGE;

@Controller
@RequestMapping("/product")
/*@SessionAttributes("cart")*/
public class LaptopController {
    @Autowired
    private LaptopService laptopService;

    @Autowired
    private ShoppingCartDTO shoppingCartDTO;

    @Autowired
    private CategoryService categoryService;

    public static void main(String[] args) {
        Long a = 1L;
        Long b = 1L;
        if (a == b) System.out.println("T");
    }

    @GetMapping
    public String list(/*@ModelAttribute("cart") List<ShoppingCartDTO> cart,*/
            Model model,
            @RequestParam(required = false) Optional<String> keyword,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "laptopId,desc") String[] sortBy,
            HttpServletRequest request) {
        Date before = new Date();
        List<OrderDetailDTO> cart = shoppingCartDTO.getShoppingCart(request);
        Page<LaptopDTO> pages = laptopService.listByPaged(keyword.orElse(""),
                page.orElse(0),
                size.orElse(ITEM_PER_PAGE),
                sortBy);
        System.out.println(new Date().getTime() - before.getTime());
        PageInfo pageInfo = new PageInfo()
                .setMaxPageItem(pages.getSize())
                .setPage(pages.getNumber() + 1)
                .setTotalPage(pages.getTotalPages())
                .setTotalItem(pages.getTotalElements());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", pages.getContent());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("cart", cart);
        model.addAttribute("message", model.asMap().get("message"));
        return "client/product/list";
    }

    @GetMapping("/categories/{category-id}")
    @ResponseBody
    public Page<LaptopDTO> getLaptopsByCategory
            (@PathVariable("category-id") Long categoryId,
             @RequestParam(required = false) Optional<Integer> page,
             @RequestParam(required = false) Optional<Integer> size,
             @RequestParam(required = false, defaultValue = "laptopId,desc") String[] sortBy) {
        return laptopService.getLaptopsByCategory(categoryId,
                page.orElse(0),
                size.orElse(ITEM_PER_PAGE),
                sortBy);
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

    /*@ModelAttribute("cart")
    public List<ShoppingCartDTO> create(HttpServletRequest request) {
        // fix error: Cannot create a session after the response has been committed
        // followed by https://github.com/spring-projects/spring-framework/issues/17475
        request.getSession(true);
        return new ArrayList<>();
    }*/

    @GetMapping("/cart/{laptop-id}")
    @ResponseBody
    public List<OrderDetailDTO> addToCart(/*@ModelAttribute("cart") List<ShoppingCartDTO> cart,*/
            @PathVariable("laptop-id") Long id,
            HttpServletRequest request) {
        List<OrderDetailDTO> cart = shoppingCartDTO.getShoppingCart(request);
        boolean existedLaptop = false;
        for (OrderDetailDTO item : cart) {
            if (item.getLaptop().getLaptopId().equals(id)) {
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

    @PostMapping("/cart/{laptop-id}")
    @ResponseBody
    public List<OrderDetailDTO> updateCart(/*@ModelAttribute("cart") List<ShoppingCartDTO> cart,*/
            @PathVariable("laptop-id") Long id, Integer quantity,
            HttpServletRequest request) {
        List<OrderDetailDTO> cart = shoppingCartDTO.getShoppingCart(request);
        if (quantity > 0) {
            for (OrderDetailDTO item : cart) {
                if (item.getLaptop().getLaptopId().equals(id)) {
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
        List<OrderDetailDTO> cart = shoppingCartDTO.getShoppingCart(request);
        int size = cart.size();
        for (int i = 0; i < size; ++i) {
            // not permit using == to compare 2 Long object
            if (cart.get(i).getLaptop().getLaptopId().equals(id)) {
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
    // cal sum money
    // payment -> order -> order details

    @GetMapping("/test")
    @ResponseBody
    public Page<LaptopDTO> listByPaged(@RequestParam(required = false) Optional<String> keyword,
                                       @RequestParam(required = false) Optional<Integer> page,
                                       @RequestParam(required = false) Optional<Integer> size,
                                       @RequestParam(required = false, defaultValue = "laptopId,desc") String[] sortBy) {
        return laptopService.listByPaged(keyword.orElse(""),
                page.orElse(0),
                size.orElse(ITEM_PER_PAGE),
                sortBy);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createLaptop(@RequestParam MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(UPLOAD_DIR + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(UPLOAD_DIR + fileName);
    }
}
