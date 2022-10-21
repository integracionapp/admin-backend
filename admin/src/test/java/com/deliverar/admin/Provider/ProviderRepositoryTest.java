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

}