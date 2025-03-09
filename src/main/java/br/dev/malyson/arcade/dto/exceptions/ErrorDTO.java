package br.dev.malyson.arcade.dto.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

import lombok.Data;

@Data
public class ErrorDTO {
    
    String error;

    public ErrorDTO(String error){
        this.error = error;
    }

    public ErrorDTO(List<FieldError> errors){
        for (FieldError fE : errors) {
            error += fE.getDefaultMessage();
        }
    }

}
