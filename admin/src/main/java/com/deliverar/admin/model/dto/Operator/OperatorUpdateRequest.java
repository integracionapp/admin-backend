package com.deliverar.admin.model.dto.Operator;

import com.deliverar.admin.model.dto.Address.AddressUpdateRequest;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperatorUpdateRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private BigInteger dni;
    private String phone;
    private String email;
    private Date birthDate;
    private Date registerDate;
    private AddressUpdateRequest address;

}
