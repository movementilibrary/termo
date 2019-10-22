package br.com.dasa.api.termo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.dasa.api.termo.enumeration.StatusTermUse;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

@Entity
@Table(name = "term_of_user")
public class TermOfUser implements Serializable {

	private static final long serialVersionUID = 5976190674890475517L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "login_user", nullable = false)
	private String loginUser;
	
	@Column(name = "description_term", columnDefinition="TEXT")
	private String descriptionTerm;

	@Column(name = "summary_term", nullable = false)
	private String summaryTerm;

	@Temporal(TemporalType.TIME)
	@Column(name = "current_date_term", nullable = false)
	private Date currentDate = new Date();


	@Column(name = "version_term", nullable = false)
	private String version;

	@Enumerated(EnumType.STRING)
	@Column(name = "status_term", nullable = false)
	private StatusTermUse status;

	public TermOfUser() {
	}

	public TermOfUser(String loginUser, String descriptionTerm, String summaryTerm, StatusTermUse status) {
		this.loginUser = loginUser;
		this.descriptionTerm = descriptionTerm;
		this.summaryTerm = summaryTerm;
		this.status = status;
	}

	public TermOfUser(String loginUser, String descriptionTerm, String summaryTerm, StatusTermUse status, String version) {
		this.loginUser = loginUser;
		this.descriptionTerm = descriptionTerm;
		this.summaryTerm = summaryTerm;
		this.status = status;
		this.version = version;
	}

	public TermOfUser(String loginUser, String descriptionTerm, String summaryTerm, Date currentDate, String version,
					  StatusTermUse status) {
		this.loginUser = loginUser;
		this.descriptionTerm = descriptionTerm;
		this.summaryTerm = summaryTerm;
		this.currentDate = currentDate;
		this.version = version;
		this.status = status;
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

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
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


}
