package com.deliverar.admin.model.dto.Franchise;


import com.deliverar.admin.model.dto.Address.AddressRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "FranchiseRequest")
public class FranchiseRequest {

    @Schema(description = "Franchise Name", example = "RestaurantExample", required = true)
    @NotBlank(message = "Introducir el nombre de la franquicia")
    private String name;

    @Schema(description = "Description of the franchise", example = "Established in ...", required = true)
    @NotBlank(message = "Introducir una descripción")
    private String description;

    @Schema(description = "Phone number", example = "4356-3927", required = true)
    @NotBlank(message = "Intoducir el número de telefono")
    private String phone;

    @Schema(description = "CUIT", example = "20234124327", required = true)
    @Range(min = 11111111111L, max = 99999999999L, message = "El CUIT debe contar con 11 digitos")
    @NotNull(message = "Introducir el CUIT")
    private BigInteger cuit;

    @Schema(description = "WebPage of the Franchise", example = "franchiseExample.com")
    private String webPageUrl;

    @Schema(description = "List of Franchise addresses", required = true)
    private List<AddressRequest> addresses;
}
