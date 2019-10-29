package br.com.dasa.api.termo.entity.json;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AceiteTermoJson {

    @JsonProperty("ID_TERMO")
    private Long idTermo;
    @JsonProperty("MDM_ID")
    private String mdmId;
    @JsonProperty("CIP")
    private Integer cip;
    @JsonProperty("RESPOSTA_CLIENTE")
    private boolean respostaCliente;

    public AceiteTermoJson() {

    }

    public AceiteTermoJson(Long idTermo, String mdmId, Integer cip, boolean respostaCliente) {
        this.idTermo = idTermo;
        this.mdmId = mdmId;
        this.cip = cip;
        this.respostaCliente = respostaCliente;
    }

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

	public Integer getCip() {
		return cip;
	}

	public void setCip(Integer cip) {
		this.cip = cip;
	}

	public void setRespostaCliente(boolean respostaCliente) {
        this.respostaCliente = respostaCliente;
    }
}
