package br.com.dasa.api.termo.entity.json;

import br.com.dasa.api.termo.enumeration.StatusTermUse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class TermoOfUserJson {

    @JsonProperty("DESCRICAO_TERMO")
    private String descriptionTerm;

    @JsonProperty("RESUMO_TERMO")
    private String summaryTerm;

    @JsonProperty("STATUS")
    private StatusTermUse status;

    @JsonProperty("USUARIO")
    private String loginUser;

    @JsonProperty("FLAG_ATUALIZACAO")
    private Boolean flagAtualizacao;

    TermoOfUserJson() {
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

    public StatusTermUse getStatus() {
        return status;
    }

    public void setStatus(StatusTermUse status) {
        this.status = status;
    }

    public Boolean getFlagAtualizacao() {
        return flagAtualizacao;
    }

    public void setFlagAtualizacao(Boolean flagAtualizacao) {
        this.flagAtualizacao = flagAtualizacao;
    }
}
