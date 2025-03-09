package br.dev.malyson.arcade.repository.auth;

import java.util.Optional;

import br.dev.malyson.arcade.model.auth.Usuario;
import br.dev.malyson.arcade.repository.base.GenericRepository;

public interface UsuarioRepository extends GenericRepository<Usuario, Long>{
    
    Optional<Usuario> findByLogin(String login);

}
