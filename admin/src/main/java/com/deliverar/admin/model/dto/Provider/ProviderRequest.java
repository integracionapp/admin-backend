package com.deliverar.admin.model.dto.Provider;

import com.deliverar.admin.model.entity.Address;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

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
    private String webPageUrl;
    private List<Address> addresses;
}
