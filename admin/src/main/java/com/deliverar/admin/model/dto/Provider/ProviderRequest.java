package com.deliverar.admin.model.dto.Provider;

import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProviderRequest {

    private String businessName;
    private BigInteger cuit;
    private String phone;
    private String email;

    //TODO Falta agregar la direccion


}
