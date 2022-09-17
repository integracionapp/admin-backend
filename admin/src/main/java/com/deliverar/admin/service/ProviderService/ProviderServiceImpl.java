package com.deliverar.admin.service.ProviderService;

import com.deliverar.admin.mappers.ProviderMapper;
import com.deliverar.admin.model.dto.Provider.ProviderRequest;
import com.deliverar.admin.model.dto.Provider.ProviderResponse;
import com.deliverar.admin.model.entity.Provider;
import com.deliverar.admin.repository.ProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public ProviderResponse saveNewProvider(ProviderRequest p) {

        //Mapper to the entity

        //Logic

        //Mapper to the response
        return null;
    }

    @Override
    public Provider update(Provider p) {
        return null;
    }

    @Override
    public void delete(Provider p) {

    }

    @Override
    public List<Provider> getAllProviders() {
        return null;
    }

    @Override
    public Provider getProviderById(Long id) {
        return null;
    }
}
