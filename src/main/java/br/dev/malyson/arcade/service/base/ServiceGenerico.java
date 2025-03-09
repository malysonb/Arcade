package br.dev.malyson.arcade.service.base;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.dev.malyson.arcade.model.base.AbstractEntity;
import br.dev.malyson.arcade.repository.base.GenericRepository;

import jakarta.transaction.Transactional;

/**
 * Service generico para evitar código boilerplate.
 * @author Malyson Souza - malysonb@gmail.com
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public abstract class ServiceGenerico<T extends AbstractEntity, ID extends Serializable>{

    public void delete(T entity) {
        entity.setAtivo(false);
        getRepository().save(entity);
    }

    public void deleteById(ID entityId) {
        Optional<T> op = getRepository().findById(entityId);
        if(op.isPresent()){
            T entity = op.get();
            entity.setAtivo(false);
            entity.setDataAtualizacao(LocalDateTime.now());
            getRepository().save(entity);
        }
    }

    public List<T> findAll() {
        return (List<T>) getRepository().findAll();
    }

    public Optional<T> findById(ID entityId) {
        return getRepository().findById(entityId);
    }

    public T save(T entity) {
        return (T) getRepository().save(entity);
    }

    public T update(T entity) {
        return getRepository().save(entity);
    }

    public T updateById(T entity, ID entityId) {
        Optional<T> op = getRepository().findById(entityId);
        if(op.isPresent()){
            T og = op.get();
            og.merge(entity);
            return (T) getRepository().save(og);
        }
        else
            return null;
    }

    /**
     * Deve ser sobrescrito com o repositório da entidade para que o serviço possa acessar os dados no banco.
     * @return Repositório genérico de Entidades.
     * @author Malyson Souza - malyson@gmail.com
     */
    public abstract GenericRepository<T,ID> getRepository();
    
}