package br.dev.malyson.arcade.model.emu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import br.dev.malyson.arcade.enums.EstrategiaArquivo;
import br.dev.malyson.arcade.model.base.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "game", schema = "emu")
@Getter
@Setter
public class Game extends AbstractEntity{

    private String name;
    private String description;

    @Lob
    @Column(name = "image")
    private Byte[] image;

    @Lob
    private String rom;

    @Column(name = "size_in_bytes", nullable = false)
    private int sizeInBytes;

    @Enumerated(EnumType.STRING)
    private EstrategiaArquivo estrategia;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "game")
    private List<SaveState> saveStates;

    @Override
    protected Game map(AbstractEntity a) {
        Game g = (Game) a;
        this.name = g.getName();
        this.description = g.getDescription();
        this.image = g.getImage();
        this.rom = g.getRom();
        this.sizeInBytes = g.getSizeInBytes();
        this.estrategia = g.getEstrategia();
        this.profile = g.getProfile();
        this.saveStates = g.getSaveStates();
        return this;
    }

    public void insertFile(byte[] file){
        if(estrategia == EstrategiaArquivo.BASE64){
            this.rom = new String(file);
            this.sizeInBytes = file.length;
        }else if(estrategia == EstrategiaArquivo.BINARIO){
            this.rom = Base64.getEncoder().encodeToString(file);
            this.sizeInBytes = this.rom.length();
        }else if(estrategia == EstrategiaArquivo.LOCAL){
            String caminho = System.getenv("GAME_PATH") + "/" + profile.getNickname() + "/" + this.name;
            try (FileOutputStream fos = new FileOutputStream(caminho)) {
                fos.write(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.rom = caminho;
            this.sizeInBytes = 0;
        }else if(estrategia == EstrategiaArquivo.CDN){
            this.rom = new String(file);
            this.sizeInBytes = 0;
        }
    }

}
