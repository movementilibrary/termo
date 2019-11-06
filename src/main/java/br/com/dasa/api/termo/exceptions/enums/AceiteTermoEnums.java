package br.com.dasa.api.termo.exceptions.enums;

public enum  AceiteTermoEnums {

    ID_NULL( "O ID DO TERMO NÃO PODE SER NULLO"),

    ID_ZERO("O ID NÃO PODE SER MENOR OU IGUAL A ZERO '0' "),

    ID_NAO_ENCONTRADO( "CODIGO INFORMADO DO TERMO NÃO FOI ENCONTRADO "),

    TERMO_NULLO("O TERMO ESTÁ NULLO"),

    MDM_ID_NULLO("O MDM_ID NÃO PODE SER NULLO"),

    CIP_INVALIDA("CIP NÃO PODE CONTER LETRAS APENAS NUMEROS"),

    STATUS_INATIVO("NÃO PODE ACEITAR UM TERMO INATIVO "),

    ID_TERMO_NULLO("O ID DO TERMO ESTÁ COM O ID NULLO");

    private final String msg;

    AceiteTermoEnums( String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
    
}
