package com.deliverar.admin.service.ProviderService;

import com.deliverar.admin.model.entity.Provider;

import java.util.List;

public interface ProvideService {

    Provider save(Provider p);
    Provider update(Provider p);
    void delete(Provider p);
    List<Provider> getAllProviders();
    Provider getProviderById(Long id);
}
