package com.khanhdpdx.webapishoplaptop.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class PageInfo {
    private int page;
    private int maxPageItem;
    private long totalItem;
    private int totalPage;
}
