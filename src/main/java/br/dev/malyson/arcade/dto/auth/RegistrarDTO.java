package br.dev.malyson.arcade.dto.auth;

import java.io.Serializable;

import br.dev.malyson.arcade.model.auth.Usuario;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * DTO básico de registro de usuário.
 * @author Malyson Souza
 * @since 2024
 * @version 1.0
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrarDTO implements Serializable{
    
    private String nome;
    private String login;
    private String senha;
    private String email;

    public RegistrarDTO() {}

    public RegistrarDTO(Usuario user) {
        this.nome = user.getNome();
        this.login = user.getLogin();
        this.email = user.getEmail();
    }
    
}
