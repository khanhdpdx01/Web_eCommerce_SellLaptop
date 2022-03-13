package com.khanhdpdx.webapishoplaptop.controller;

import com.khanhdpdx.webapishoplaptop.dto.ApiResponse;
import com.khanhdpdx.webapishoplaptop.dto.OrderDetailDTO;
import com.khanhdpdx.webapishoplaptop.dto.ShoppingCartDTO;
import com.khanhdpdx.webapishoplaptop.dto.laptop.CreateLaptopDTO;
import com.khanhdpdx.webapishoplaptop.dto.laptop.LaptopDTO;
import com.khanhdpdx.webapishoplaptop.dto.pagination.PaginationMapper;
import com.khanhdpdx.webapishoplaptop.dto.pagination.PaginationResponse;
import com.khanhdpdx.webapishoplaptop.service.CategoryService;
import com.khanhdpdx.webapishoplaptop.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static com.khanhdpdx.webapishoplaptop.constant.PaginationConstant.ITEM_PER_PAGE;
import static com.khanhdpdx.webapishoplaptop.constant.StatusCode.CREATE_ITEM_SUCCESS;

@Controller
@RequestMapping("/api/v1/product")
/*@SessionAttributes("cart")*/
public class LaptopController {
    @Autowired
    private LaptopService laptopService;

    @Autowired
    private ShoppingCartDTO shoppingCartDTO;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> list(/*@ModelAttribute("cart") List<ShoppingCartDTO> cart,*/
            @RequestParam(required = false) Optional<String> keyword,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "laptopId,desc") String[] sortBy) {

        Page<LaptopDTO> pages = laptopService.listByPaged(keyword.orElse(""),
                page.orElse(0),
                size.orElse(ITEM_PER_PAGE),
                sortBy);

        PaginationResponse<LaptopDTO> response = PaginationMapper.MAPPER.from(pages);

        return ResponseEntity.ok(response);
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

    @GetMapping("/{slug}")
    @ResponseBody
    public ResponseEntity findOne(@PathVariable String slug) {
        LaptopDTO laptopDTO = laptopService.findBySlug(slug);
        return ResponseEntity.ok(laptopDTO);
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createLaptop(@ModelAttribute CreateLaptopDTO createLaptopDTO) {
        laptopService.save(createLaptopDTO);
        return ResponseEntity.ok(new ApiResponse(true, CREATE_ITEM_SUCCESS));
    }
}
