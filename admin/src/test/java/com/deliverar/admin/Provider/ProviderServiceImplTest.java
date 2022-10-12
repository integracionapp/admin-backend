package com.deliverar.admin.Provider;

import com.deliverar.admin.mappers.ProviderMapper;
import com.deliverar.admin.model.dto.Address.AddressResponse;
import com.deliverar.admin.model.dto.Provider.ProviderRequest;
import com.deliverar.admin.model.dto.Provider.ProviderResponse;
import com.deliverar.admin.model.entity.Address;
import com.deliverar.admin.model.entity.Provider;
import com.deliverar.admin.repository.ProviderRepository;
import com.deliverar.admin.service.ProviderService.ProviderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProviderServiceImplTest {


    @Autowired
    private ProviderService providerService;

    @Autowired
    private ProviderRepository providerRepository;

    @Test
    void saveNewProvider() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

   /* @Test
    void getAllProviders() {
        //when
        providerService.getAllProviders();

        //then
        verify(providerRepository).findAll();


    }

    */

    @Test
    void findById() {
        Provider providerExpected = this.getProviderResponseDummy();
        Provider providerResponse = providerService.findById(1L);

        if (providerExpected.getId() == providerResponse.getId())
            System.out.println("Exito");
    }

    private Provider getProviderResponseDummy(){
        List<Address> addresses = new ArrayList<>();
        addresses.add(this.getAddressDummy());

        Provider provider = Provider.builder()
                .id(1L)
                .businessName("Marolio")
                .cuit(new BigInteger("27280335148"))
                .phone("4268-0214")
                .email("example@gmail.com")
                .address(addresses)
                .build();

        providerRepository.save(provider);
        return provider;
    }

    private Address getAddressDummy(){
        Address address = Address.builder()
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

    @Test // Repetido ?
    void getProviderResponseById() {
    }

    @Test
    void getProvidersByName() {
       /* ProviderResponse providerExpected = this.getProviderResponseDummy();
        ProviderResponse providerResponse = (ProviderResponse) providerService.getProvidersByName("Marolio");



        assertThat(providerResponse).usingRecursiveComparison().isEqualTo(providerExpected);
        */
    }
}