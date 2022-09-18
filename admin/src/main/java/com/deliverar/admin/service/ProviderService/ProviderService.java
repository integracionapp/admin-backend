package com.deliverar.admin.service.ProviderService;

import com.deliverar.admin.exceptions.ProviderNotFoundException;
import com.deliverar.admin.model.dto.Provider.ProviderRequest;
import com.deliverar.admin.model.dto.Provider.ProviderResponse;
import com.deliverar.admin.model.dto.Provider.ProviderUpdateRequest;
import com.deliverar.admin.model.entity.Provider;

import java.util.List;

public interface ProviderService {

    ProviderResponse saveNewProvider(ProviderRequest p);
    ProviderResponse update(ProviderUpdateRequest p);
    void delete(Long id);
    List<ProviderResponse> getAllProviders();
    Provider findById(Long id);
    ProviderResponse getProviderResponseById(Long id) throws ProviderNotFoundException;
}
