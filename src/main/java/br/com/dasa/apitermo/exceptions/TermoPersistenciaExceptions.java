package br.com.dasa.apitermo.exceptions;

public class TermoPersistenciaExceptions extends ApiException {

    public TermoPersistenciaExceptions(String msg, Exception e) {
        super(msg, e);
    }

    public TermoPersistenciaExceptions(Exception e) {
        super(e);
    }

    public TermoPersistenciaExceptions(String msg) {
        super(msg);
    }
}
