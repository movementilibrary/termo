package br.com.dasa.api.termo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
public abstract class InternalServerException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InternalServerException(String message, java.lang.Exception e) {
        super(message, e);
    }

    public InternalServerException (String message) {
        super(message);
    }
    
    
}
