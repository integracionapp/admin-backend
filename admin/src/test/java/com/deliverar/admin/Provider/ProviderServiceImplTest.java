package com.deliverar.admin.Provider;

import com.deliverar.admin.exceptions.ProviderNotFoundException;
import com.deliverar.admin.mappers.ProviderMapper;
import com.deliverar.admin.model.dto.Address.AddressRequest;
import com.deliverar.admin.model.dto.Address.AddressUpdateRequest;
import com.deliverar.admin.model.dto.Provider.ProviderRequest;
import com.deliverar.admin.model.dto.Provider.ProviderResponse;

import com.deliverar.admin.model.dto.Provider.ProviderUpdateRequest;
import com.deliverar.admin.model.entity.Address;
import com.deliverar.admin.model.entity.Provider;
import com.deliverar.admin.repository.ProviderRepository;
import com.deliverar.admin.service.ProviderService.ProviderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProviderServiceImplTest {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderService providerService;

    private final ProviderMapper providerMapper = ProviderMapper.INSTANCE;

    AddressRequest addressRequest;

    ProviderRequest providerRequest;

    ProviderUpdateRequest providerUpdateRequest;
    AddressUpdateRequest addressUpdateRequest;
    List<AddressUpdateRequest> addressUpdateRequests;

    @BeforeEach
    void setUp() {
        addressRequest = AddressRequest.builder()
                .street("Benedetto")
                .number(4521)
                .latitude("52° 31' 28'' N")
                .longitude("13° 24' 38'' E")
                .zipCode("1365")
                .city("Escobar")
                .province("Buenos Aires")
                .build();

        List<AddressRequest> addresses = new ArrayList<>();
        addresses.add(addressRequest);

        providerRequest = ProviderRequest.builder()
                .businessName("Marolio4")
                .cuit(new BigInteger("27280335148"))
                .phone("4268-0214")
                .email("example@gmail.com")
                .webPageUrl("www.example.com")
                .addresses(addresses)
                .build();
        providerService.saveNewProvider(providerRequest);

        providerUpdateRequest = new ProviderUpdateRequest();
        addressUpdateRequests = new ArrayList<>();

    }

    @Test
    void saveNewProvider() {
        ProviderResponse providerResponse = providerService.saveNewProvider(providerRequest);
        ProviderResponse providerFound = providerMapper.providerToProviderResponse(providerRepository.findByBusinessName("Marolio2"));
        assertThat(providerFound).usingRecursiveComparison().isEqualTo(providerResponse);
    }

    @Test
    void update() {
        Provider provider = providerRepository.findByBusinessName(providerRequest.getBusinessName());
        AddressRequest address = providerRequest.getAddresses().get(0);
        addressUpdateRequest = AddressUpdateRequest.builder()
                .city(address.getCity())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .number(address.getNumber())
                .province(address.getProvince())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .id(provider.getAddress().get(0).getId())
                .build();
         addressUpdateRequests.add(addressUpdateRequest);

        providerUpdateRequest = ProviderUpdateRequest.builder()
                .businessName(providerRequest.getBusinessName()+"Act")
                .id(provider.getId())
                .email(provider.getEmail())
                .phone(provider.getPhone())
                .cuit(provider.getCuit())
                .addresses(addressUpdateRequests)
                .build();

        providerService.update(providerUpdateRequest);
        provider = providerRepository.findByBusinessName(providerUpdateRequest.getBusinessName());
        assertThat(provider.getBusinessName()).isEqualTo(providerUpdateRequest.getBusinessName());

    }

    @Test
    void delete() {
        ProviderResponse providerResponse = providerService.saveNewProvider(providerRequest);
        providerService.delete(providerResponse.getId());
        List<ProviderResponse> providers = providerService.getAllProviders();
        for(ProviderResponse p: providers){
            assertThat(p.getId()).isNotEqualTo(providerResponse.getId());
        }
    }

    @Test
    void getAllProviders() {
        List<ProviderResponse> providersS = providerService.getAllProviders();
        List<ProviderResponse> providersR = providerMapper.providerToProviderResponse(providerRepository.findAll());
        int x = 0;
        for (ProviderResponse p : providersS) {
            assertThat(p.getId()).isEqualTo(providersR.get(x).getId());
            x++;
        }
    }

    @Test
    void findById() {
        Long longInteger = new Long(1L);
        Provider provider = providerService.findById(longInteger);
        assertThat(provider.getId()).usingRecursiveComparison().isEqualTo(longInteger);
    }

    @Test
    void getProviderResponseById() {
        Long longInteger = new Long(1L);
        ProviderResponse providerResponse = providerService.getProviderResponseById(longInteger);
        assertThat(providerResponse.getId()).usingRecursiveComparison().isEqualTo(longInteger);
    }

    @Test
    void getProvidersByName() {
        String name = "Marolio";
        List<ProviderResponse> providers = providerService.getProvidersByName(name);
        for(ProviderResponse p: providers) {
            assertThat(p.getBusinessName(),containsString(name));
            System.out.println("Entidad");
        }
    }
}