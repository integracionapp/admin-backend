package com.deliverar.admin.Provider;

import com.deliverar.admin.mappers.ProviderMapper;
import com.deliverar.admin.model.dto.Address.AddressRequest;
import com.deliverar.admin.model.dto.Provider.ProviderRequest;
import com.deliverar.admin.repository.ProviderRepository;
import com.deliverar.admin.service.ProviderService.ProviderService;
import com.deliverar.admin.service.ProviderService.ProviderServiceImpl;
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
import static org.junit.jupiter.api.Assertions.*;


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
                .businessName("Marolio2")
                .cuit(new BigInteger("27280335148"))
                .phone("4268-0214")
                .email("example@gmail.com")
                .webPageUrl("www.example.com")
                .addresses(addresses)
                .build();
    }

    @Test
    void saveNewProvider() {
        providerService.saveNewProvider(providerRequest);
        assertThat(providerRepository.findByBusinessName("Marolio2")).usingRecursiveComparison().isEqualTo(providerMapper.providerRequestToProvider(providerRequest));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAllProviders() {
    }

    @Test
    void findById() {
    }

    @Test
    void getProviderResponseById() {
    }

    @Test
    void getProvidersByName() {
    }
}