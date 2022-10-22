package com.deliverar.admin.model.dto.Provider;

import com.deliverar.admin.model.dto.Address.AddressRequest;
import com.deliverar.admin.model.entity.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.math.BigInteger;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @Schema(name = "ProviderRequest")
public class ProviderRequest {

    @Schema(description = "Business Name", example = "Marolio", required = true)
    @NotBlank(message = "Introducir un nombre")
    private String businessName;

    @Schema(description = "CUIT", example = "27280335148", required = true)
    @Range(min = 11111111111L, max = 99999999999L, message = "CUIT must have 11 digits") //TODO Check
    @NotNull(message = "Introducir un CUIT válido")
    private BigInteger cuit;

    @Schema(description = "Contact phone", example = "4268-0214", required = true)
    @NotBlank(message = "Introducir un número de teléfono")
    private String phone;

    @Schema(description = "Contact Email Address", example = "example@gmail.com", required = true)
    @Email(message = "Introducir un e-mail válido")
    private String email;

    @Schema(description = "Website URL", example = "www.example.com")
    private String webPageUrl;

    @Schema(description = "List of Provider address", required = true)
    private List<AddressRequest> addresses;
}
