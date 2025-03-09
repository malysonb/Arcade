package br.dev.malyson.arcade.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.caelum.stella.ValidationMessage;

/**
 * Erro básico de validação.
 * @author Malyson Souza - malysonb@gmail.com
 * @version 1.0
 * @since 1.0
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidacaoException extends RuntimeException {

    List<String> mensagens = null;

    public ValidacaoException(String mensagem){
        super("Erro de validação: ");
        this.mensagens = new ArrayList<String>();
        this.mensagens.add(mensagem);
    }

    public ValidacaoException(List<ValidationMessage> mensagens){
        super("Erro de validação: ");
        this.mensagens = new ArrayList<String>();
        for (ValidationMessage validationMessage : mensagens) {
            this.mensagens.add(validationMessage.getMessage());
        }
    }

    public List<String> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<String> mensagens) {
        this.mensagens = mensagens;
    }

}
