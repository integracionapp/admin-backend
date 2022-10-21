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
