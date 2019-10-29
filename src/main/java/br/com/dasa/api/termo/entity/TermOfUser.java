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

@Entity
@Table(name = "term_of_user")
public class TermOfUser implements Serializable {

	private static final long serialVersionUID = 5976190674890475517L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "login_user", nullable = false)
	private String loginUser;
	
	@Column(name = "description_term", columnDefinition="TEXT")
	private String descriptionTerm;

	@Column(name = "summary_term", nullable = false)
	private String summaryTerm;

	@Temporal(TemporalType.TIME)
	@Column(name = "current_date_term", nullable = false)
	private Date currentDate;

	@Column(name = "version_term", nullable = false)
	private String version;


	@Column(name = "flag", nullable = false)
	private boolean flag;

	@Enumerated(EnumType.STRING)
	@Column(name = "status_term", nullable = false)
	private StatusTermUse status;

	public TermOfUser() {
	}


	public TermOfUser(Long id, String loginUser, String descriptionTerm, String summaryTerm, Date currentDate, String version, boolean flag, StatusTermUse status) {
		this.id = id;
		this.loginUser = loginUser;
		this.descriptionTerm = descriptionTerm;
		this.summaryTerm = summaryTerm;
		this.currentDate = currentDate;
		this.version = version;
		this.flag = flag;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public StatusTermUse getStatus() {
		return status;
	}

	public void setStatus(StatusTermUse status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TermOfUser [id=" + id + ", loginUser=" + loginUser + ", descriptionTerm=" + descriptionTerm
				+ ", summaryTerm=" + summaryTerm + ", currentDate=" + currentDate + ", version=" + version + ", status="
				+ status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentDate == null) ? 0 : currentDate.hashCode());
		result = prime * result + ((descriptionTerm == null) ? 0 : descriptionTerm.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((loginUser == null) ? 0 : loginUser.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((summaryTerm == null) ? 0 : summaryTerm.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TermOfUser other = (TermOfUser) obj;
		if (currentDate == null) {
			if (other.currentDate != null)
				return false;
		} else if (!currentDate.equals(other.currentDate))
			return false;
		if (descriptionTerm == null) {
			if (other.descriptionTerm != null)
				return false;
		} else if (!descriptionTerm.equals(other.descriptionTerm))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loginUser == null) {
			if (other.loginUser != null)
				return false;
		} else if (!loginUser.equals(other.loginUser))
			return false;
		if (status != other.status)
			return false;
		if (summaryTerm == null) {
			if (other.summaryTerm != null)
				return false;
		} else if (!summaryTerm.equals(other.summaryTerm))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}
