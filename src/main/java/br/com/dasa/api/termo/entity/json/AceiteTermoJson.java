package br.com.dasa.api.termo.entity.json;

import com.fasterxml.jackson.annotation.JsonProperty;


public class AceiteTermoJson {

    @JsonProperty("id_termo")
    private Long idTermo;
    @JsonProperty("mdm_id")
    private String mdmId;
    @JsonProperty("cip")
    private Long cip;
    @JsonProperty("resposta_cliente")
    private boolean respostaCliente;

    public AceiteTermoJson() {

    }

    public AceiteTermoJson(Long idTermo, String mdmId, Long cip, boolean respostaCliente) {
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

	public Long getCip() {
		return cip;
	}

	public void setCip(Long cip) {
		this.cip = cip;
	}

	public void setRespostaCliente(boolean respostaCliente) {
        this.respostaCliente = respostaCliente;
    }
	
}
