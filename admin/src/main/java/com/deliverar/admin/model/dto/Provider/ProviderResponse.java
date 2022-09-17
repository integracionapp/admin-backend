package com.deliverar.admin.model.dto.Provider;

import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProviderResponse {

    private Long id;
    private String businessName;
    private BigInteger cuit;
}
