package com.deliverar.admin.model.dto.Operator;

import com.deliverar.admin.model.dto.Address.AddressRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "OperatorRequest")
public class OperatorRequest {

    @Schema(description = "First Name", example = "Juan", required = true)
    @NotBlank(message = "First name is required")
    private String firstName;

    @Schema(description = "Last Name", example = "Perez", required = true)
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Schema(description = "Gender", example = "Male", required = true)
    @NotBlank(message = "Gender is required")
    private String gender;

    @Schema(description = "DNI", example = "42684753", required = true)
    @Range(min = 1000000, max = 99999999, message = "DNI must have 8 digits")
    @NotNull(message = "DNI cannot be null")
    private BigInteger dni;

    @Schema(description = "Contact phone", example = "4268-0214", required = true)
    @NotBlank(message = "Phone Number is required")
    private String phone;

    @Schema(description = "Contact Email Address", example = "example@gmail.com", required = true)
    @Email(message = "Use a Valid Email")
    private String email;

    @Schema(description = "Birth Date", example = "26/03/2001", required = true)
    @NotNull(message = "Birth Date cannot be null")
    private Date birthDate;

    @Schema(description = "Register Date", example = "25/08/2020", required = true)
    @NotNull(message = "Register date cannot be null")
    private Date registerDate;

    @Schema(description = "List of Operator Addresses", required = true)
    private List<AddressRequest> addresses;

}
