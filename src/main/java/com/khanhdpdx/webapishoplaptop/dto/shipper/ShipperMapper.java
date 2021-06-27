package com.khanhdpdx.webapishoplaptop.dto.shipper;

import com.khanhdpdx.webapishoplaptop.entity.Shipper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ShipperMapper {
    ShipperMapper MAPPER = Mappers.getMapper(ShipperMapper.class);

    ShipperDTO from(Shipper shipper);

    List<ShipperDTO> fromShippers(List<Shipper> shippers);
}
