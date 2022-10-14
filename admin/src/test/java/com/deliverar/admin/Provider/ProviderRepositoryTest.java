package com.deliverar.admin.Provider;

import com.deliverar.admin.model.entity.Address;
import com.deliverar.admin.model.entity.Provider;
import com.deliverar.admin.repository.ProviderRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProviderRepositoryTest {

    @Autowired
    private ProviderRepository providerRepository;

    List<Provider> providers = new ArrayList<>();
    String name;
    @BeforeEach
    void setUp() {
        name = "Marolio";
    }

    @Test
    void shouldReturnArrayWithAllProvidersContainingSomeName(){
        providers = providerRepository.findByBusinessNameContaining(name);
        for(Provider p: providers){
            assertTrue(p.getBusinessName().contains(name));
        }

    }
    /*
    List<Provider> setUp() {
        List<Provider> p= new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        Address address = Address.builder()
                .street("Benedetto")
                .number(4521)
                .latitude("52° 31' 28'' N")
                .longitude("13° 24' 38'' E")
                .zipCode("1365")
                .city("Escobar")
                .province("Buenos Aires")
                .build();
        addresses.add(address);
        Provider prueba = Provider.builder()
                .businessName("Marolio")
                .cuit(new BigInteger("27280335148"))
                .phone("4268-0214")
                .email("example@gmail.com")
                .webPageUrl("www.example.com")
                .address(addresses)
                .build();
        p.add(prueba);
        return p;
    }

     */

}