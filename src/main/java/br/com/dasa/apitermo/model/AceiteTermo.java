package br.com.dasa.apitermo.model;

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
    private Date dataAceite;

    private String mdmIdCliente;

    private Boolean respotaCliente;

    @OneToOne
    private Termo termo;

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

    public Boolean getRespotaCliente() {
        return respotaCliente;
    }

    public void setRespotaCliente(Boolean respotaCliente) {
        this.respotaCliente = respotaCliente;
    }

    public Termo getTermo() {
        return termo;
    }

    public void setTermo(Termo termo) {
        this.termo = termo;
    }
}
