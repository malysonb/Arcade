package br.dev.malyson.arcade.dto.auth;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * Objeto utilizado para login. contém o suficiente para autenticar um usuário.
 * @author Malyson Souza
 * @since 2024
 * @version 1.0
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDTO {
    
    private String login;
    private String senha;

}
