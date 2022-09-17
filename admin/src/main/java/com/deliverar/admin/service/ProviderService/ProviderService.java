package com.deliverar.admin.service.ProviderService;

import com.deliverar.admin.model.dto.Provider.ProviderRequest;
import com.deliverar.admin.model.dto.Provider.ProviderResponse;
import com.deliverar.admin.model.entity.Provider;

import java.util.List;

public interface ProviderService {

    ProviderResponse saveNewProvider(ProviderRequest p);
    Provider update(Provider p);
    void delete(Provider p);
    List<Provider> getAllProviders();
    Provider getProviderById(Long id);
}
