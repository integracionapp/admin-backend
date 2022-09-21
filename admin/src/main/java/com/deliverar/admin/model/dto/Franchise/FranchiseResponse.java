package com.deliverar.admin.model.dto.Franchise;

import com.deliverar.admin.model.dto.Address.AddressResponse;
import com.deliverar.admin.model.entity.Address;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FranchiseResponse {

    private Long id;

    private String name;
    private String description;
    private String phone;
    private BigInteger cuit;
    private String webPageUrl;
    private List<AddressResponse> addresses;


}
