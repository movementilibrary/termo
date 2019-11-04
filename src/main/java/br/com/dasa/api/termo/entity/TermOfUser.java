package br.com.dasa.api.termo.entity;

import br.com.dasa.api.termo.enumeration.StatusTermUse;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "term_of_user")
@SuppressWarnings("serial")
public class TermOfUser {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "login_user", nullable = false)
	private String loginUser;
	
	@Column(name = "description_term", columnDefinition="TEXT")
	private String descriptionTerm;

	@Column(name = "summary_term", columnDefinition="TEXT", nullable = false)
	private String summaryTerm;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "current_date_term", nullable = false)
	private Date currentDate = new Date();


	@Column(name = "version_term", nullable = false)
	private String version;

	@Enumerated(EnumType.STRING)
	@Column(name = "status_term", nullable = false)
	private StatusTermUse status;

	@Column(name = "flag_atualizacao", nullable = false)
	private boolean flagAtualizacao;


	public TermOfUser() {
	}


	public TermOfUser(Long id, String loginUser, String descriptionTerm, String summaryTerm, Date currentDate, String version, StatusTermUse status, boolean flagAtualizacao) {
		this.id = id;
		this.loginUser = loginUser;
		this.descriptionTerm = descriptionTerm;
		this.summaryTerm = summaryTerm;
		this.currentDate = currentDate;
		this.version = version;
		this.status = status;
		this.flagAtualizacao = flagAtualizacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
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

	public void setSummaryTerm(String summaryTerm) {
		this.summaryTerm = summaryTerm;
	}

	public Date getCurrentDate() {
		return currentDate;
	}


	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public StatusTermUse getStatus() {
		return status;
	}

	public void setStatus(StatusTermUse status) {
		this.status = status;
	}

	public boolean isFlagAtualizacao() {
		return flagAtualizacao;
	}

	public void setFlagAtualizacao(boolean flagAtualizacao) {
		this.flagAtualizacao = flagAtualizacao;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate; 
		
	}
}
