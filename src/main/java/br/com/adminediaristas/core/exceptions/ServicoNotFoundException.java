package br.com.adminediaristas.core.exceptions;

import javax.persistence.EntityNotFoundException;

public class ServicoNotFoundException extends EntityNotFoundException {

    public ServicoNotFoundException(String message) {
        super(message);
    }
}
