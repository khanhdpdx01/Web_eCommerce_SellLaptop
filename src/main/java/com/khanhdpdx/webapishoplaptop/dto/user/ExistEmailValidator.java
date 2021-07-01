package com.khanhdpdx.webapishoplaptop.dto.user;

import com.khanhdpdx.webapishoplaptop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistEmailValidator implements ConstraintValidator<ExistEmail, String> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(ExistEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        // ignore check exist email
        if(email.isEmpty() || "".equals(email)) return true;
        return !userService.existEmail(email);
    }
}
