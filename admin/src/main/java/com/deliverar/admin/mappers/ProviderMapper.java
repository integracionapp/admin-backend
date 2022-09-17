package com.deliverar.admin.mappers;

import com.deliverar.admin.model.dto.Provider.ProviderRequest;
import com.deliverar.admin.model.dto.Provider.ProviderResponse;
import com.deliverar.admin.model.entity.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProviderMapper {

    ProviderMapper INSTANCE = Mappers.getMapper(ProviderMapper.class);


    Provider providerRequestToProvider(ProviderRequest providerRequest);
    ProviderResponse ProviderToProviderResponse(Provider provider);

}
