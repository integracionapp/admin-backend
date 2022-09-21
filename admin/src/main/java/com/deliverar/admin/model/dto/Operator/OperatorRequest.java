package com.deliverar.admin.model.dto.Operator;

import com.deliverar.admin.model.dto.Address.AddressRequest;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperatorRequest {

    private String firstName;
    private String lastName;
    private String gender;
    private BigInteger dni;
    private String phone;
    private String email;
    private Date birthDate;
    private Date registerDate;
    private AddressRequest address;

}
