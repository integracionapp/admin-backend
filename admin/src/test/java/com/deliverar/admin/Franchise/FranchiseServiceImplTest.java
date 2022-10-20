package com.deliverar.admin.Franchise;

import com.deliverar.admin.mappers.FranchiseMapper;
import com.deliverar.admin.model.dto.Address.AddressRequest;
import com.deliverar.admin.model.dto.Address.AddressUpdateRequest;
import com.deliverar.admin.model.dto.Franchise.FranchiseRequest;
import com.deliverar.admin.model.dto.Franchise.FranchiseResponse;
import com.deliverar.admin.model.dto.Franchise.FranchiseUpdateRequest;
import com.deliverar.admin.model.entity.Franchise;
import com.deliverar.admin.repository.FranchiseRepository;
import com.deliverar.admin.service.FranchiseService.FranchiseService;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FranchiseServiceImplTest {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private FranchiseService franchiseService;

    private final FranchiseMapper franchiseMapper = FranchiseMapper.INSTANCE;

    AddressRequest addressRequest;
    FranchiseRequest franchiseRequest;
    List<AddressRequest> addressRequests;

    FranchiseUpdateRequest franchiseUpdateRequest;
    AddressUpdateRequest addressUpdateRequest;
    List<AddressUpdateRequest> addressUpdateRequests;

    @BeforeEach
    void setUp() {
        addressRequests = new ArrayList<>();
        addressUpdateRequests = new ArrayList<>();
        franchiseUpdateRequest = new FranchiseUpdateRequest();

        addressRequest = AddressRequest.builder()
                .street("Benedetto")
                .number(4521)
                .latitude("52° 31' 28'' N")
                .longitude("13° 24' 38'' E")
                .zipCode("1365")
                .city("Escobar")
                .province("Buenos Aires")
                .build();


        addressRequests.add(addressRequest);

        franchiseRequest = FranchiseRequest.builder()
                .addresses(addressRequests)
                .webPageUrl("www.example.com")
                .cuit(new BigInteger("27280335148"))
                .phone("4268-0214")
                .description("ExampleDescription")
                .name("ExampleName")
                .build();
    }

    @Test
    void saveNewFranchise() {
        FranchiseResponse franchiseResponse = franchiseService.saveNewFranchise(franchiseRequest);
        FranchiseResponse franchiseFound = franchiseMapper.franchiseToFranchiseResponse(franchiseRepository.findByName(franchiseRequest.getName()));
        assertThat(franchiseFound).usingRecursiveComparison().isEqualTo(franchiseResponse);

    }

    @Test
    void update() {
        franchiseService.saveNewFranchise(franchiseRequest);
        Franchise franchise = franchiseRepository.findByName(franchiseRequest.getName());
        addressRequest = franchiseRequest.getAddresses().get(0);
        addressUpdateRequest = AddressUpdateRequest.builder()
                .city(addressRequest.getCity())
                .latitude(addressRequest.getLatitude())
                .longitude(addressRequest.getLongitude())
                .number(addressRequest.getNumber())
                .province(addressRequest.getProvince())
                .street(addressRequest.getStreet())
                .zipCode(addressRequest.getZipCode())
                .id(franchise.getAddress().get(0).getId())
                .build();
        addressUpdateRequests.add(addressUpdateRequest);

        franchiseUpdateRequest = FranchiseUpdateRequest.builder()
                .addresses(addressUpdateRequests)
                .name(franchise.getName()+"Act")
                .description(franchise.getDescription())
                .phone(franchise.getPhone())
                .cuit(franchise.getCuit())
                .webPageUrl(franchise.getWebPageUrl())
                .id(franchise.getId())
                .build();

        franchiseService.update(franchiseUpdateRequest);
        franchise =  franchiseRepository.findByName(franchiseUpdateRequest.getName());
        assertThat(franchise.getName()).isEqualTo(franchiseUpdateRequest.getName());

    }

    @Test
    void deleteById() {
        FranchiseResponse franchiseResponse = franchiseService.saveNewFranchise(franchiseRequest);
        franchiseService.deleteById(franchiseResponse.getId());
        List<FranchiseResponse> franchises = franchiseService.getAllFranchises();
        for(FranchiseResponse f: franchises){
            assertThat(f.getId()).isNotEqualTo(franchiseResponse.getId());
        }
    }

    @Test
    void findById() {
        franchiseService.saveNewFranchise(franchiseRequest);
        Long longInteger = new Long(1L);
        Franchise franchise = franchiseService.findById(longInteger);
        assertThat(franchise.getId()).usingRecursiveComparison().isEqualTo(longInteger);
    }

    @Test
    void getFranchiseResponseById() {
        franchiseService.saveNewFranchise(franchiseRequest);
        Long longInteger = new Long(1L);
        FranchiseResponse franchiseResponse = franchiseService.getFranchiseResponseById(longInteger);
        assertThat(franchiseResponse.getId()).usingRecursiveComparison().isEqualTo(longInteger);
    }

    @Test
    void getAllFranchises() {
        List<FranchiseResponse> franchisesS = franchiseService.getAllFranchises();
        List<FranchiseResponse> franchisesR = franchiseMapper.franchiseToFranchiseResponse(franchiseRepository.findAll());
        int x = 0;
        for (FranchiseResponse f : franchisesS){
            assertThat(f.getId()).isEqualTo(franchisesR.get(x).getId());
            x++;
        }
    }

    @Test
    void getFranchisesByName() {
        String name = "ExampleName";
        List<FranchiseResponse> franchises = franchiseService.getFranchisesByName(name);
        for(FranchiseResponse f: franchises){
            assertThat(f.getName(),containsString(name));
        }
    }
}