package br.dev.malyson.arcade.model.auth;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.dev.malyson.arcade.model.base.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Usuário padrão utilizado para autenticação no sistema.
 * @author Malyson Souza - malysonb@gmail.com
 * @version 1.0
 */
@Entity
@Table(schema = "auth", name = "usuario")
public class Usuario extends AbstractEntity implements UserDetails{
    
    private String login;

    private String nome;

    private String email;

    private String senha;

    private boolean ativado;

    @ManyToOne
    @JoinColumn(name = "role_name", nullable = false)
    private Role nivel;

    public Usuario() {
    }

    /* ---------------------------------------------------------- */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(this.nivel);
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ativado;
    }

    /* -------------------------------------------------------- */

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivado() {
        return ativado;
    }

    public void setAtivado(boolean ativado) {
        this.ativado = ativado;
    }

    public Role getNivel() {
        return nivel;
    }

    public void setNivel(Role nivel) {
        this.nivel = nivel;
    }

    @Override
    protected Usuario map(AbstractEntity a) {
        Usuario u = (Usuario) a;
        this.login = u.getLogin();
        this.nome = u.getNome();
        this.email = u.getEmail();
        if(u.getSenha() != null && !u.getSenha().isEmpty())
            this.senha = new BCryptPasswordEncoder().encode(u.getSenha());
        this.nivel = u.getNivel();
        return this;
    }

}
