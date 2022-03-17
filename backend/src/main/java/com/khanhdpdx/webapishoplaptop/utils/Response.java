package com.khanhdpdx.webapishoplaptop.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response {
    private boolean state;
    private String message;
    private Object data;
}
