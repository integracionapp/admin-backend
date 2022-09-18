package com.deliverar.admin.service.ProviderService;

import com.deliverar.admin.exceptions.ProviderNotFoundException;
import com.deliverar.admin.mappers.ProviderMapper;
import com.deliverar.admin.model.dto.Provider.ProviderRequest;
import com.deliverar.admin.model.dto.Provider.ProviderResponse;
import com.deliverar.admin.model.dto.Provider.ProviderUpdateRequest;
import com.deliverar.admin.model.entity.Provider;
import com.deliverar.admin.repository.ProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    private final ProviderMapper providerMapper = ProviderMapper.INSTANCE;

    @Override
    public ProviderResponse saveNewProvider(ProviderRequest p) {
        log.info("Saving new Provider");
        Provider provider = providerMapper.providerRequestToProvider(p);
        provider = providerRepository.save(provider);
        log.info("New provider was saved successfully");

        return providerMapper.providerToProviderResponse(provider);
    }

    @Override
    public ProviderResponse update(ProviderUpdateRequest p) {
        log.info("Updating provider with ID {}", p.getId());
        Provider providerEntity = providerRepository.save(providerMapper.providerUpdateRequestToProvider(p));
        log.info("Provider (ID {}) updated successfully", p.getId());
        return providerMapper.providerToProviderResponse(providerEntity);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting Provider with ID {}", id);
        Provider p = this.findById(id);
        providerRepository.delete(p);
        log.info("Provider (ID {}) deleted successfully");
    }

    @Override
    public List<ProviderResponse> getAllProviders() {
        log.info("Getting all the providers");
        return providerMapper.providerToProviderResponse(providerRepository.findAll());
    }

    @Override
    public Provider findById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new ProviderNotFoundException("Provider not found with ID "+id));
    }

    @Override
    public ProviderResponse getProviderResponseById(Long id) throws ProviderNotFoundException {
        log.info("Getting Provider with id", id);
        Provider p = this.findById(id);
        log.info("Provider found with ID {}", id);
        return providerMapper.providerToProviderResponse(p);
    }
}
