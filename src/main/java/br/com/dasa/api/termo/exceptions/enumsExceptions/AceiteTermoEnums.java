package br.com.dasa.api.termo.exceptions.enumsExceptions;

public enum  AceiteTermoEnums {

    ID_NULL(1, "O ID DO TERMO NÃO PODE SER NULLO"),
    ID_NAO_ENCONTRADO(1, "CODIGO NÃO ENCONTRADO ");

    private final Integer cod;
    private final String msg;


    AceiteTermoEnums(Integer cod , String msg) {
        this.cod = cod;
        this.msg = msg;
    }

    public Integer getCod() {
        return cod;
    }

    public String getMsg() {
        return msg;
    }
}
