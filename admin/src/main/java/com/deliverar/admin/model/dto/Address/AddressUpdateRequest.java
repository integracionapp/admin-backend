package com.deliverar.admin.model.dto.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "AddressUpdateRequest")
public class AddressUpdateRequest {

    @Schema(description = "Address ID", example = "1", required = true)
    @NotBlank(message = "Address ID is required")
    private Long id;

    @Schema(description = "Street Number", example = "4521", required = true)
    @NotBlank(message = "Street Number is required")
    private String street;

    @Schema(description = "Street Number", example = "4521", required = true)
    @NotBlank(message = "Street Number is required")
    private Integer number;

    @Schema(description = "Latitude", example = "52° 31' 28'' N", required = true)
    @NotBlank(message = "Latitude is required")
    private String latitude;

    @Schema(description = "Longitude", example = "13° 24' 38'' E", required = true)
    @NotBlank(message = "Longitude is required")
    private String longitude;

    @Schema(description = "Zip Code", example = "1365", required = true)
    @NotBlank(message = "Zip Code is required")
    private String zipCode;

    @Schema(description = "City", example = "Escobar", required = true)
    @NotBlank(message = "City is required")
    private String city;

    @Schema(description = "Province", example = "Buenos Aires", required = true)
    @NotBlank(message = "Province is required")
    private String province;
}
