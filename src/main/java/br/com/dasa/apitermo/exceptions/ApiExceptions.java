package br.com.dasa.apitermo.exceptions;


public abstract class ApiExceptions extends RuntimeException {

    public ApiExceptions(String message) {
        super(message);
    }
}
