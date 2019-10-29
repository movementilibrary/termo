package br.com.dasa.api.termo.entity;

import javax.persistence.*;

@Entity
@Table(name = "version")
public class VersionTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;


    public VersionTerm() {
    }

    public VersionTerm(String description) {
        this.description = description;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
