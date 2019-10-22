package br.com.dasa.api.termo.exceptions;



public abstract class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApiException(String msg, Exception e) {
        super(msg, e);
    }

    public ApiException(String msg){
        super(msg);
    }



    public abstract String getErro();
}
