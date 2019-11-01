package br.com.dasa.api.termo.entity.json;

import br.com.dasa.api.termo.enumeration.StatusTermUse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class TermoOfUserJson {

    @JsonProperty("descricao_termo")
    private String descriptionTerm;

    @JsonProperty("resumo_termo")
    private String summaryTerm;

    @JsonProperty("usuario")
    private String loginUser;

    @JsonProperty("flag_atualizacao")
    private Boolean flagAtualizacao;

    TermoOfUserJson() {
    }

    public TermoOfUserJson(String descriptionTerm, String summaryTerm, String loginUser, Boolean flagAtualizacao) {
        this.descriptionTerm = descriptionTerm;
        this.summaryTerm = summaryTerm;
        this.loginUser = loginUser;
        this.flagAtualizacao = flagAtualizacao;
    }

    public String getDescriptionTerm() {
        return descriptionTerm;
    }

    public void setDescriptionTerm(String descriptionTerm) {
        this.descriptionTerm = descriptionTerm;
    }

    public String getSummaryTerm() {
        return summaryTerm;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public void setSummaryTerm(String summaryTerm) {
        this.summaryTerm = summaryTerm;
    }

    public Boolean getFlagAtualizacao() {
        return flagAtualizacao;
    }

    public void setFlagAtualizacao(Boolean flagAtualizacao) {
        this.flagAtualizacao = flagAtualizacao;
    }
}
