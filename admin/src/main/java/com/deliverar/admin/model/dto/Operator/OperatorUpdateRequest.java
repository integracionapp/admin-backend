package com.deliverar.admin.model.dto.Operator;

import com.deliverar.admin.model.dto.Address.AddressUpdateRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "OperatorUpdateRequest")
public class OperatorUpdateRequest {

    @Schema(description = "Operator ID", required = true, example = "1")
    private Long id;

    @Schema(description = "First Name", example = "Juan", required = true)
    @NotBlank(message = "Introducir el nombre")
    private String firstName;

    @Schema(description = "Last Name", example = "Perez", required = true)
    @NotBlank(message = "Introducir el apellido")
    private String lastName;

    @Schema(description = "Gender", example = "Male", required = true)
    @NotBlank(message = "Especifique su sexo")
    private String gender;

    @Schema(description = "DNI", example = "42684753", required = true)
    @Range(min = 1000000, max = 99999999, message = "DNI must have 8 digits")
    @NotNull(message = "Introducir un DNI válido")
    private BigInteger dni;

    @Schema(description = "Contact phone", example = "4268-0214", required = true)
    @NotBlank(message = "Introducir el número de télefono")
    private String phone;

    @Schema(description = "Contact Email Address", example = "example@gmail.com", required = true)
    @Email(message = "Introducir un e-mail válido")
    private String email;

    @Schema(description = "Birth Date", example = "26/03/2001", required = true)
    @NotNull(message = "Introducir una fecha de nacimiento válida")
    private LocalDateTime birthDate;

    @Schema(description = "Register Date", example = "25/08/2020", required = true)
    @NotNull(message = "Introducir una fecha válida")
    private LocalDateTime registerDate;

    @Schema(description = "List of Operator Addresses", required = true)
    private List<AddressUpdateRequest> addresses;

}
