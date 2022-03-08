package com.khanhdpdx.webapishoplaptop.service.impl;

import com.khanhdpdx.webapishoplaptop.dto.shipper.ShipperDTO;
import com.khanhdpdx.webapishoplaptop.dto.shipper.ShipperMapper;
import com.khanhdpdx.webapishoplaptop.repository.ShipperRepository;
import com.khanhdpdx.webapishoplaptop.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipperServiceImpl implements ShipperService {
    @Autowired
    private ShipperRepository shipperRepository;

    public List<ShipperDTO> findAll() {
        List<ShipperDTO> shipperDTOs = ShipperMapper.MAPPER.fromShippers(shipperRepository.findAll());
        return shipperDTOs;
    }
}
