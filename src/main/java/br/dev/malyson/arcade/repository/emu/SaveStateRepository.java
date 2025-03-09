package br.dev.malyson.arcade.repository.emu;

import java.util.Optional;

import br.dev.malyson.arcade.model.emu.SaveState;
import br.dev.malyson.arcade.repository.base.GenericRepository;

public interface SaveStateRepository extends GenericRepository<SaveState, Long>{

    Optional<SaveState> findByName(String name);

}
