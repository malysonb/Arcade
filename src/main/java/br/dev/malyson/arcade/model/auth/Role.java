package br.dev.malyson.arcade.model.auth;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe utilizada para os papeis de autenticação no sistema.
 * @author Malyson Souza - malysonb@gmail.com
 * @version 1.0
 */
@Entity
@Table(schema = "auth", name = "role")
public class Role implements GrantedAuthority{
    
    @Id
    @Column(unique = true)
    private String nivel;

    public Role(){

    }

    public Role(String nivel){
        this.nivel = nivel;
    }

    @Override
    public String getAuthority() {
        return this.nivel;
    }
}
