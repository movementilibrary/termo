package br.com.dasa.api.termo.entity.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class BuscaAceiteTermoJson implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "termo_aceite")
	private boolean termoAceite;

	public BuscaAceiteTermoJson(boolean termoAceite) {
		this.termoAceite = termoAceite; 
	}
	
	public boolean isTermoAceite() {
		return termoAceite;
	}

	public void setTermoAceite(boolean termoAceite) {
		this.termoAceite = termoAceite;
	}
	
}
