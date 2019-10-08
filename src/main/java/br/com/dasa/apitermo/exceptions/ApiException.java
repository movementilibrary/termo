package br.com.dasa.apitermo.exceptions;


public abstract class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

     ApiException(String msg, Exception e) {
        super(msg, e);
    }

     ApiException(Exception e) {
        super(e);
    }

     ApiException(String msg) {
        super(msg);
    }
}
