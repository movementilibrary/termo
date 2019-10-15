package br.com.dasa.api.termo.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

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

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_ID_TERMO"), nullable = false)
	private TermOfUser termOfUser;

	public AceiteTermo(String mdmIdCliente, Boolean respostaCliente, TermOfUser termOfUser) {
		this.mdmIdCliente = mdmIdCliente;
		this.respostaCliente = respostaCliente;
		this.termOfUser = termOfUser;
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

	public void setTermOfUser(TermOfUser termOfUser) {
		this.termOfUser = termOfUser;
	}
}