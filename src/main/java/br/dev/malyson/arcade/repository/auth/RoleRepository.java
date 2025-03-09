package br.dev.malyson.arcade.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.malyson.arcade.model.auth.Role;

public interface RoleRepository  extends JpaRepository<Role, String>{
    
}
