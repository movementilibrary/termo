package br.com.dasa.apitermo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TERMO")
@Data
public class Termo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descricao;
    private String versao;

//    @Temporal(TemporalType.TIMESTAMP)
    private String dataModificacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(String dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
}
