package br.com.dasa.api.termo.exceptions;



public abstract class ApiException extends RuntimeException {

    public ApiException(String msg, Exception e) {
        super(msg, e);
    }

    public ApiException(String msg){
        super(msg);
    }
    public ApiException(Exception e){
            super(e);
    }

    public ApiException() {

    }

    public abstract ErroJson getErro();
}
