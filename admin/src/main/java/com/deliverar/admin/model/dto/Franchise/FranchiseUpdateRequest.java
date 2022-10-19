package com.deliverar.admin.model.dto.Franchise;


import com.deliverar.admin.model.dto.Address.AddressRequest;
import com.deliverar.admin.model.dto.Address.AddressUpdateRequest;
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
@Schema(name = "FranchiseUpdateRequest")
public class FranchiseUpdateRequest {


    @Schema(description = "Franchise ID", example = "1", required = true)
    @NotBlank(message = "Franchise ID is required")
    private Long id;

    @Schema(description = "Franchise Name", example = "RestaurantExample", required = true)
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Description of the franchise", example = "Established in ...", required = true)
    @NotBlank(message = "Desciption is required")
    private String description;

    @Schema(description = "Phone number", example = "4356-3927", required = true)
    @NotBlank(message = "Phone is required")
    private String phone;

    @Schema(description = "CUIT", example = "20234124327", required = true)
    @Range(min = 11111111111L, max = 99999999999L, message = "CUIT must have 11 digits")
    @NotNull(message = "CUIT cannot be NULL")
    private BigInteger cuit;

    @Schema(description = "WebPage of the Franchise", example = "franchiseExample.com")
    private String webPageUrl;

    @Schema(description = "List of Franchise addresses", required = true)
    private List<AddressUpdateRequest> addresses;
}
