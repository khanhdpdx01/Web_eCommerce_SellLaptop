package com.khanhdpdx.webapishoplaptop.service;

import com.khanhdpdx.webapishoplaptop.dto.shipper.ShipperDTO;

import java.util.List;

public interface ShipperService {
    List<ShipperDTO> findAll();
}
