package br.com.dasa.api.termo.exceptions;

import br.com.dasa.api.termo.exceptions.enumsExceptions.AceiteTermoEnums;
import org.springframework.util.StringUtils;

import java.util.Date;

public class ErroJson {

    private static final long serialVersionUID = 1L;
    private Integer cod;
    private String descricao;
    private Date data;

    private ErroJson(String descricao, Date data) {
        this.data = new Date();
        this.descricao = descricao;
    }

    public ErroJson(AceiteTermoEnums erroEnum, String descricao, Date data) {
        this.cod = erroEnum.getCod();
        this.descricao = !StringUtils.isEmpty(descricao)? erroEnum.getMsg().concat(": ").concat(descricao) : erroEnum.getMsg();
        this.data = data;
    }


    public ErroJson(AceiteTermoEnums erroEnum, Date data) {
        this.cod = erroEnum.getCod();
        this.descricao = erroEnum.getMsg();
        this.data = data;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public static ErroJson of(String message) {

        ErroJson json = new ErroJson (message , new Date ());

        return json;
    }
}
