package br.com.dasa.api.termo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class AceiteTermo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date dataAceite = new Date();

	private String mdmIdCliente;

	private Boolean respostaCliente;

	private Long cip;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_ID_TERMO"), nullable = false)
	private TermOfUser termOfUser;

	public AceiteTermo() {
	}

	public AceiteTermo(String mdmIdCliente, Boolean respostaCliente, TermOfUser termOfUser, Long cip) {
		this.mdmIdCliente = mdmIdCliente;
		this.respostaCliente = respostaCliente;
		this.termOfUser = termOfUser;
		this.cip = cip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAceite() {
		return dataAceite;
	}

	public void setDataAceite(Date dataAceite) {
		this.dataAceite = dataAceite;
	}

	public String getMdmIdCliente() {
		return mdmIdCliente;
	}

	public void setMdmIdCliente(String mdmIdCliente) {
		this.mdmIdCliente = mdmIdCliente;
	}

	public Boolean getRespostaCliente() {
		return respostaCliente;
	}

	public void setRespostaCliente(Boolean respostaCliente) {
		this.respostaCliente = respostaCliente;
	}

	public TermOfUser getTermOfUser() {
		return termOfUser;
	}

	public Long getCip() {
		return cip;
	}

	public void setCip(Long cip) {
		this.cip = cip;
	}

	public void setTermOfUser(TermOfUser termOfUser) {
		this.termOfUser = termOfUser;
	}
}
