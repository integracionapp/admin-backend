package com.deliverar.admin.model.dto.Provider;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class ProviderRequest {

    private String street;
    private Integer number;
    private String latitude;
    private String longitude;
    private String zipCode;
    private String city;
    private String province;

    //TODO Falta agregar la direccion


}
