package com.khanhdpdx.webapishoplaptop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ControllerAdvisor {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

/*    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("exception_type", ex.getClass().getName());

        logger.error(ex.getClass().getName() + ": " + ex.getMessage());
        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }*/
}
