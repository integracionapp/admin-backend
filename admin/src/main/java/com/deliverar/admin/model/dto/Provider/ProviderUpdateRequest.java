package com.deliverar.admin.model.dto.Provider;

import com.deliverar.admin.model.dto.Address.AddressRequest;
import com.deliverar.admin.model.dto.Address.AddressResponse;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProviderUpdateRequest {

    private Long id;
    private String businessName;
    private BigInteger cuit;
    private String phone;
    private String email;
    private String webPageUrl;
    private List<AddressRequest> addresses;
}
