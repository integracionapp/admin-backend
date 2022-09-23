package com.deliverar.admin.mappers;

import com.deliverar.admin.model.dto.Franchise.FranchiseRequest;
import com.deliverar.admin.model.dto.Franchise.FranchiseResponse;
import com.deliverar.admin.model.dto.Franchise.FranchiseUpdateRequest;
import com.deliverar.admin.model.entity.Franchise;
import jdk.dynalink.linker.LinkerServices;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FranchiseMapper {

    FranchiseMapper INSTANCE = Mappers.getMapper(FranchiseMapper.class);

    @Mapping(source = "franchiseRequest.addresses",target = "address")
    Franchise franchiseRequestToFranchise(FranchiseRequest franchiseRequest);

    @Mapping(source = "franchise.address", target = "addresses")
    FranchiseResponse franchiseToFranchiseResponse(Franchise franchise);

    @Mapping(source = "franchiseUpdateRequest.addresses", target = "address")
    Franchise franchiseUpdateRequestToFranchise(FranchiseUpdateRequest franchiseUpdateRequest);

    @Mapping(source = "franchiseList.address",target = "addresses")
    List<FranchiseResponse> franchiseToFranchiseResponse(List<Franchise> franchiseList);
}
