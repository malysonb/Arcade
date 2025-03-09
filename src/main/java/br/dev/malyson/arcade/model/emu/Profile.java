package br.dev.malyson.arcade.model.emu;

import java.util.List;

import br.dev.malyson.arcade.enums.EstrategiaArquivo;
import br.dev.malyson.arcade.enums.Tier;
import br.dev.malyson.arcade.model.auth.Usuario;
import br.dev.malyson.arcade.model.base.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "profile", schema = "emu")
@Getter
@Setter
public class Profile extends AbstractEntity{
    
    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    @Column(name = "used_storage_bytes")
    private int usedStorageBytes;

    @OneToMany(mappedBy = "profile")
    private List<Game> games;

    @OneToMany(mappedBy = "profile")
    private List<SaveState> saveStates;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    @Override
    protected Profile map(AbstractEntity a) {
        Profile p = (Profile) a;
        this.nickname = p.getNickname();
        this.tier = p.getTier();
        this.usedStorageBytes = p.getUsedStorageBytes();
        this.games = p.getGames();
        this.saveStates = p.getSaveStates();
        this.usuario = p.getUsuario();
        return this;
    }

    public int getTotalStorageBytes(){
        return this.tier.getStorageBytes();
    }

    public int getAvailableStorageBytes(){
        return this.getTotalStorageBytes() - this.usedStorageBytes;
    }

    public void addUsedStorageBytes(int bytes){
        this.usedStorageBytes += bytes;
    }

    public void removeUsedStorageBytes(int bytes){
        this.usedStorageBytes -= bytes;
    }

    public void canAddStorageBytes(int bytes) throws RuntimeException{
        if(this.getAvailableStorageBytes() < bytes){
            throw new RuntimeException("Não há espaço suficiente para enviar este jogo.\n" +
            "Espaço disponível: " + (this.getAvailableStorageBytes() * 1024 * 1024) + " MB " +
            "Espaço necessário: " + (bytes * 1024 * 1024) + " MB.");
        }
    }
    
    public void calculateUsedStorageBytes(){
        this.usedStorageBytes = 0;
        for(Game g : this.games){
            if(g.getEstrategia() != EstrategiaArquivo.CDN)
                this.usedStorageBytes += g.getSizeInBytes();
        }
        for(SaveState s : this.saveStates){
            this.usedStorageBytes += s.getSizeInBytes();
        }
    }
    
}
