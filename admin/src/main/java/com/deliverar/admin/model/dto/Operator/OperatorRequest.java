package com.deliverar.admin.model.dto.Operator;

import com.deliverar.admin.model.dto.Address.AddressRequest;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDateTime;
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
    @NotBlank(message = "Introducir el nombre")
    private String firstName;

    @Schema(description = "Last Name", example = "Perez", required = true)
    @NotBlank(message = "Introducir el apellido")
    private String lastName;

    @Schema(description = "Gender", example = "Male", required = true)
    @NotBlank(message = "Especifique su sexo")
    private String gender;

    @Schema(description = "DNI", example = "42684753", required = true)
    @Range(min = 1000000, max = 99999999, message = "El DNI debe contar con 8 digitos como maximo y 7 como minimo")
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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime birthDate;

    @Schema(description = "Register Date", example = "25/08/2020", required = true)
    @NotNull(message = "Introducir una fecha válida")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime registerDate;

    @Schema(description = "List of Operator Addresses", required = true)
    private List<AddressRequest> addresses;

}
