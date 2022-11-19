package com.deliverar.admin.model.dto.Message;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class ProviderMessage {

    private String tipo;
    private String tipoDocumento;
    private String numeroDocumento;
    private String razonSocial;
    private String domicilio;
    private String telefono;
    private String correoElectronico;
}
