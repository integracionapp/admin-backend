package com.deliverar.admin.initializer;

import com.deliverar.admin.model.entity.Address;
import com.deliverar.admin.model.entity.Provider;
import com.deliverar.admin.repository.ProviderRepository;
import com.deliverar.admin.service.ProviderService.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ProviderDataLoader implements ApplicationRunner {

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("Provider DataLoader - Populating Provider Table");
        this.createProvider();
        log.info("Provider DataLoader - Populate completed");
    }

    private void createProvider(){
        List<Address> addresses = new ArrayList<>();
        addresses.add(this.getAddressDummy());

        Provider provider = Provider.builder()
                .businessName("Marolio")
                .cuit(new BigInteger("27280335148"))
                .phone("4268-0214")
                .email("example@gmail.com")
                .webPageUrl("www.example.com")
                .address(addresses)
                .build();

        providerRepository.save(provider);

    }

    private Address getAddressDummy(){
        Address address = Address.builder()
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
