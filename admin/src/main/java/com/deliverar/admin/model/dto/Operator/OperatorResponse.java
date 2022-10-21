package com.deliverar.admin.model.dto.Operator;

import com.deliverar.admin.model.dto.Address.AddressResponse;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperatorResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private BigInteger dni;
    private String phone;
    private String email;
    private LocalDateTime birthDate;
    private LocalDateTime registerDate;
    private List<AddressResponse> addresses;

}
