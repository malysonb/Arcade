package br.dev.malyson.arcade.repository.base;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.dev.malyson.arcade.model.base.AbstractEntity;

@NoRepositoryBean
public interface GenericRepository<T extends AbstractEntity, ID extends Serializable> extends CrudRepository<T, ID>{
    
    

}