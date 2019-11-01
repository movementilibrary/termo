package br.com.dasa.api.termo.entity;

import javax.persistence.*;

@Entity
@Table(name = "version")
public class VersionTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer version;


    public VersionTerm() {
    }

    public VersionTerm( Integer version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
