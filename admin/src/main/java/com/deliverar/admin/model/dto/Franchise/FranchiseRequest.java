package com.deliverar.admin.model.dto.Franchise;


import com.deliverar.admin.model.dto.Address.AddressRequest;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FranchiseRequest {

    private String name;
    private String description;
    private String phone;
    private BigInteger cuit;
    private String webPageUrl;
    private List<AddressRequest> addresses;
}
