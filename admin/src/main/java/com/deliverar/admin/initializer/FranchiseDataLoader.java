package com.deliverar.admin.initializer;

import com.deliverar.admin.model.entity.Address;
import com.deliverar.admin.model.entity.Franchise;
import com.deliverar.admin.repository.FranchiseRepository;
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
public class FranchiseDataLoader implements ApplicationRunner {

    @Autowired
    private FranchiseRepository franchiseRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("Franchise DataLoader - Populating Franchise Table");
        this.createFranchise();
        log.info("Franchise DataLoader - Populate completed");
    }

    private void createFranchise(){
       /* List<Address> addresses = new ArrayList<>();
        addresses.add(this.getAddressDummy());

        Franchise franchise = Franchise.builder()
                .name("RestaurantExample")
                .description("Established in ...")
                .phone("4356-3927")
                .cuit(new BigInteger("20234124327"))
                .webPageUrl("franchiseExample.com")
                .address(addresses)
                .build();

        franchiseRepository.save(franchise);
        */
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
