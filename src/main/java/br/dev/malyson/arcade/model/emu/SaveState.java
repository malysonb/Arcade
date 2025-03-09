package br.dev.malyson.arcade.model.emu;

import br.dev.malyson.arcade.enums.EstrategiaArquivo;
import br.dev.malyson.arcade.model.base.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "save_state", schema = "emu")
@Getter
@Setter
public class SaveState extends AbstractEntity{
    
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "data", nullable = false)
    private String data;

    @Enumerated(EnumType.STRING)
    private EstrategiaArquivo estrategia;

    @Column(name = "size_in_bytes", nullable = false)
    private int sizeInBytes;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Override
    protected AbstractEntity map(AbstractEntity a) {
        // TODO Auto-generated method stub
        return null;
    }

}
