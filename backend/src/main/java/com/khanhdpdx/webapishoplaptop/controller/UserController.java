package com.khanhdpdx.webapishoplaptop.controller;

import com.khanhdpdx.webapishoplaptop.dto.user.UserDTO;
import com.khanhdpdx.webapishoplaptop.security.JwtRequest;
import com.khanhdpdx.webapishoplaptop.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        model.addAttribute("jwtRequest", new JwtRequest());
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("url_prior_login", referrer);
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin/admin";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(HttpSession session) {
        return session.getId();
    }

    @GetMapping("/register")
    public String getFormRegister(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model,
                           @ModelAttribute("user") @Valid UserDTO user,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register";
        }

        userService.register(user);
        redirectAttributes.addFlashAttribute("message", "Đăng kí tài khoản thành công");
        return "redirect:/product";
    }

    @GetMapping("/statistic")
    @PreAuthorize("hasRole('ADMIN')")
    public String statistic() {
        return "statistic_covid";
    }

}

