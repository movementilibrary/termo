package br.com.dasa.api.termo.exceptions;

import br.com.dasa.api.termo.exceptions.enums.AceiteTermoEnums;

public class AceiteException extends RuntimeException {

    private final AceiteTermoEnums termoEnums;

    public AceiteException() {
        termoEnums = null;
    }

    public AceiteException(AceiteTermoEnums aceiteTermoEnums) {

      this.termoEnums = aceiteTermoEnums;
    }

    public AceiteException(String message) {
        termoEnums = null;
    }

    @Override
    public String getMessage() {
        assert termoEnums != null;
        return termoEnums.getMsg();
    }
}

