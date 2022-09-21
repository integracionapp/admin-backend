package com.deliverar.admin.mappers;

import com.deliverar.admin.model.dto.Franchise.FranchiseRequest;
import com.deliverar.admin.model.dto.Franchise.FranchiseResponse;
import com.deliverar.admin.model.entity.Franchise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FranchiseMapper {

    FranchiseMapper INSTANCE = Mappers.getMapper(FranchiseMapper.class);

    @Mapping(source = "franchiseRequest.addresses",target = "address")
    Franchise franchiseRequestToFranchise(FranchiseRequest franchiseRequest);

    @Mapping(source = "franchise.address", target = "addresses")
    FranchiseResponse franchiseToFranchiseResponse(Franchise franchise);
}
