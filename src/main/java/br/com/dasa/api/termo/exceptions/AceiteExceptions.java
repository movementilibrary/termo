package br.com.dasa.api.termo.exceptions;

public class AceiteExceptions extends RuntimeException {

    private String erroIdTermo;


    public AceiteExceptions(String descricaoErro) {

        this.erroIdTermo = descricaoErro;
    }


    public String getMessage() {

        return String.format("O '%s' do termo não pode ser nullo", erroIdTermo);
    }
}

