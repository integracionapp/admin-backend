package com.deliverar.admin.initializer;

import com.deliverar.admin.model.dto.User.RoleRequest;
import com.deliverar.admin.model.dto.User.UserRequest;
import com.deliverar.admin.model.entity.*;
import com.deliverar.admin.repository.FranchiseRepository;
import com.deliverar.admin.repository.OperatorRepository;
import com.deliverar.admin.repository.ProviderRepository;
import com.deliverar.admin.service.UserService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component @Slf4j @RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final UserService userService;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.saveRole(new RoleRequest("ROLE_ADMIN"));
        userService.saveRole(new RoleRequest( "ROLE_PROVIDER"));
        userService.saveRole(new RoleRequest( "ROLE_FRANCHISE"));
        userService.saveRole(new RoleRequest( "ROLE_OPERATOR"));
        userService.saveRole(new RoleRequest("ROLE_ACCOUNTABLE"));

        userService.saveUser(new UserRequest("Gonzalo Bari", "gnb", "1234", new ArrayList<>()));
        userService.saveUser(new UserRequest( "Franco Siciliano", "sicilian", "1234", new ArrayList<>()));
        userService.saveUser(new UserRequest("Gonzalo Juliano", "julian", "1234", new ArrayList<>()));
        userService.saveUser(new UserRequest("Juan Iviglia", "pampa", "1234", new ArrayList<>()));

        userService.addRoleToUser("gnb", "ROLE_ADMIN");
        userService.addRoleToUser("sicilian", "ROLE_PROVIDER");
        userService.addRoleToUser("julian", "ROLE_OPERATOR");
        userService.addRoleToUser("pampa", "ROLE_ACCOUNTABLE");

        providerDataLoader();
        operatorDataLoader();
    }

    private void operatorDataLoader(){
        log.info("Operator DataLoader - Populating Operator Table");
        this.createOperator();
        log.info("Operator DataLoader - Populate completed");
    }

    private void providerDataLoader(){
        log.info("Provider DataLoader - Populating Provider Table");
        this.createProvider();
        log.info("Provider DataLoader - Populate completed");
    }

    private void franchiseDataLoader(){
        log.info("Franchise DataLoader - Populating Franchise Table");
        this.createFranchise();
        log.info("Franchise DataLoader - Populate completed");
    }

    private void createFranchise(){
        List<Address> addresses = new ArrayList<>();
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
    }

    private void createOperator(){
        List<Address> addresses = new ArrayList<>();
        addresses.add(this.getAddressDummy());

        User user = userService.findByUsername("julian");

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
                .user(user)
                .build();

        operatorRepository.save(operator);

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
