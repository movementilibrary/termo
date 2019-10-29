package br.com.dasa.api.termo.exceptions;

import br.com.dasa.api.termo.exceptions.enums.AceiteTermoEnums;

public class AceiteExceptions extends RuntimeException {

    private final AceiteTermoEnums termoEnums;

    public AceiteExceptions() {
        termoEnums = null;
    }

    public AceiteExceptions(AceiteTermoEnums aceiteTermoEnums) {

      this.termoEnums = aceiteTermoEnums;
    }

    public AceiteExceptions(String message) {
        termoEnums = null;
    }

    @Override
    public String getMessage() {
        assert termoEnums != null;
        return termoEnums.getMsg();
    }
}

