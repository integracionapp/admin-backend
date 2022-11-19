package com.deliverar.admin.model.dto.Message;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperatorMessage {

    private String tipo;
    private String nombre;
    private String apellido;
    private String numeroDocumento;
    private String telefono;
    private String email;
    private String domicilio;
    private String username;
    private String password;
}
