package com.deliverar.admin.Franchise;

import com.deliverar.admin.model.entity.Address;
import com.deliverar.admin.model.entity.Franchise;
import com.deliverar.admin.repository.FranchiseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class FranchiseRepositoryTest {

    @Autowired
    private FranchiseRepository franchiseRepository;

    List<Franchise> franchises = new ArrayList<>();

    String name;
    @BeforeEach
    void setUp() {
       /* List<Address> addresses = new ArrayList<>();
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

        Franchise prueba = Franchise.builder()
                .name("RestaurantExample")
                .description("Established in ...")
                .phone("4356-3927")
                .cuit(new BigInteger("20234124327"))
                .webPageUrl("franchiseExample.com")
                .address(addresses)
                .build();

        frachises.add(prueba);
        */

        name = "RestaurantExample";
    }

    @Test
    void shouldReturnArrayWithAllFranchisesContainingSomeName(){
        franchises = franchiseRepository.findByNameContaining(name);
        for (Franchise f: franchises) {
            assertTrue(f.getName().contains(name));
        }

    }

}
