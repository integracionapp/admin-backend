package com.deliverar.admin.services;

import com.deliverar.admin.model.dto.Address.AddressResponse;
import com.deliverar.admin.model.dto.Provider.ProviderResponse;
import com.deliverar.admin.service.ProviderService.ProviderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProviderServiceTest {

    @Autowired
    private ProviderService providerService;

    @Test
    public void shouldReturnValidProviderResponseByProviderId(){
        ProviderResponse providerExpected = this.getProviderResponseDummy();
        ProviderResponse providerResponse = providerService.getProviderResponseById(1L);

        //assertEquals(providerExpected.getId(),providerResponse.getId());
        assertThat(providerResponse)
                .usingRecursiveComparison()
                .isEqualTo(providerExpected);
    }

    private ProviderResponse getProviderResponseDummy(){
        List<AddressResponse> addresses = new ArrayList<>();
        addresses.add(this.getAddressDummy());

        ProviderResponse provider = ProviderResponse.builder()
                .id(1L)
                .businessName("Marolio")
                .cuit(new BigInteger("27280335148"))
                .phone("4268-0214")
                .email("example@gmail.com")
                .webPageUrl("www.example.com")
                .addresses(addresses)
                .build();

        return provider;
    }

    private AddressResponse getAddressDummy(){
        AddressResponse address = AddressResponse.builder()
                .id(3L)
                .street("Benedetto")
                .number(4521)
                .latitude("52° 31' 28'' N")
                .longitude("13° 24' 38'' E")
                .zipCode("1365")
                .city("Escobar")
                .province("Buenos Aires")
                .build();

        return address;
    }
}
