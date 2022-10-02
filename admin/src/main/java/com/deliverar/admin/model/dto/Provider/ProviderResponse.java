package com.deliverar.admin.model.dto.Provider;

import com.deliverar.admin.model.dto.Address.AddressResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ProviderResponse {

    private Long id;
    private String businessName;
    private BigInteger cuit;
    private String phone;
    private String email;
    private String webPageUrl;
    private List<AddressResponse> addresses;

}
