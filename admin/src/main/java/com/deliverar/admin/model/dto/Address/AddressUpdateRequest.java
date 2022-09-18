package com.deliverar.admin.model.dto.Address;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressUpdateRequest {

    private Long id;
    private String street;
    private Integer number;
    private String latitude;
    private String longitude;
    private String zipCode;
    private String city;
    private String province;
}
