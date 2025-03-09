package br.dev.malyson.arcade.model.base;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import br.dev.malyson.arcade.exception.ValidacaoException;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @Version
    private int version;
    @JsonIgnore
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @JsonIgnore
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    @Column (name = "is_ativo", nullable = false)
    private boolean ativo;


    /**
     * Construtor padrão para entidades abstratas.
     */
    public AbstractEntity() {
        this.ativo = true;
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    public AbstractEntity merge(AbstractEntity a){
        if(!CompareClass(a))
            throw new ValidacaoException("A entidade mapeada não corresponde!");
        return map(a);
    }

    /**
     * Ao atualizar uma entidade diretamente sem DTO deve ser utilizado o map.
     * @param a Recomendado que seja o mesmo tipo da Entidade que está sendo mapeada.
     * @return
     */
    protected abstract AbstractEntity map(AbstractEntity a);

    /* ------------------------- */

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean CompareClass(AbstractEntity a){
        return a.getClass().getCanonicalName().equals(this.getClass().getCanonicalName());
    }
    
}
