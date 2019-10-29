package br.com.dasa.api.termo.entity;

import javax.persistence.*;

@Entity
@Table(name = "sub_version")
public class SubVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
