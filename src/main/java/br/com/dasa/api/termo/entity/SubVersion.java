package br.com.dasa.api.termo.entity;

import javax.persistence.*;

@Entity
@Table(name = "sub_version")
public class SubVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private  Integer subVersion;

    public SubVersion() {
    }

    public SubVersion(Integer subVersion) {
        this.subVersion = subVersion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubVersion() {
        return subVersion;
    }

    public void setSubVersion(Integer subVersion) {
        this.subVersion = subVersion;
    }
}
