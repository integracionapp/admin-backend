package com.deliverar.admin.initializer;

import com.deliverar.admin.model.entity.Address;
import com.deliverar.admin.model.entity.Franchise;
import com.deliverar.admin.model.entity.Operator;
import com.deliverar.admin.repository.FranchiseRepository;
import com.deliverar.admin.repository.OperatorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class OperatorDataLoader implements ApplicationRunner {

    @Autowired
    private OperatorRepository operatorRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("Operator DataLoader - Populating Operator Table");
        this.createOperator();
        log.info("Operator DataLoader - Populate completed");
    }

    private void createOperator(){
        List<Address> addresses = new ArrayList<>();
        addresses.add(this.getAddressDummy());

        Operator operator = Operator.builder()
                .firstName("Juan")
                .lastName("Perez")
                .gender("Male")
                .dni(new BigInteger("42684753"))
                .phone("4268-0214")
                .email("example@gmail.com")
                .birthDate(LocalDateTime.now())
                .registerDate(LocalDateTime.now())
                .addresses(addresses)
                .build();

        operatorRepository.save(operator);

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
