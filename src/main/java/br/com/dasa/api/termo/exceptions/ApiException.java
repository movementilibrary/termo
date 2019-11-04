package br.com.dasa.api.termo.exceptions;



public abstract class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApiException(String msg, Exception e) {
        super(msg, e);
    }

    public ApiException(){
        super();
    }

    public abstract String getErro();
}
