package com.deliverar.admin.repository;

import com.deliverar.admin.model.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    List<Provider> findByBusinessNameContaining(String BusinessName);
    Provider findByBusinessName(String BusinessName);

}
