package br.com.dasa.api.termo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AceiteTermoJson {

    @JsonProperty("ID_TERMO")
    private Long idTermo;
    @JsonProperty("MDM_ID")
    private String mdmId;
    @JsonProperty("REPOSTA_CLIENTE")
    private boolean respostaCliente;

    public Long getIdTermo() {
        return idTermo;
    }

    public void setIdTermo(Long idTermo) {
        this.idTermo = idTermo;
    }

    public String getMdmId() {
        return mdmId;
    }

    public void setMdmId(String mdmId) {
        this.mdmId = mdmId;
    }

    public boolean isRespostaCliente() {
        return respostaCliente;
    }

    public void setRespostaCliente(boolean respostaCliente) {
        this.respostaCliente = respostaCliente;
    }
}
