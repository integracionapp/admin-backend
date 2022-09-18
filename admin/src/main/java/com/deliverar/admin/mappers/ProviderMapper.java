package com.deliverar.admin.mappers;

import com.deliverar.admin.model.dto.Provider.ProviderRequest;
import com.deliverar.admin.model.dto.Provider.ProviderResponse;
import com.deliverar.admin.model.dto.Provider.ProviderUpdateRequest;
import com.deliverar.admin.model.entity.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProviderMapper {

    ProviderMapper INSTANCE = Mappers.getMapper(ProviderMapper.class);


    @Mapping(source = "providerRequest.addresses", target = "address")
    Provider providerRequestToProvider(ProviderRequest providerRequest);

    @Mapping(source = "provider.address", target = "addresses")
    ProviderResponse providerToProviderResponse(Provider provider);

    @Mapping(source = "p.addresses", target = "address")
    Provider providerUpdateRequestToProvider(ProviderUpdateRequest p);

    @Mapping(source = "p.address", target = "addresses")
    List<ProviderResponse> providerToProviderResponse(List<Provider> p);

}
