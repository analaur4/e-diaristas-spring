package br.com.adminediaristas.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class UsuarioNotFoundException extends EntityNotFoundException {

    public UsuarioNotFoundException(String message) {
        super(message);
    }
}
