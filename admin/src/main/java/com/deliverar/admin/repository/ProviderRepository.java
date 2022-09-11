package com.deliverar.admin.repository;

import com.deliverar.admin.model.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
