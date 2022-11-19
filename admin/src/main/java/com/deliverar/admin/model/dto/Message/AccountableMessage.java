package com.deliverar.admin.model.dto.Message;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountableMessage {

    private String tipo;
    private String nombre;
    private String username;
    private String password;
}
