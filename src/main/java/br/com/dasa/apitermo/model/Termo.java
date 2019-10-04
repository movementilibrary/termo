package br.com.dasa.apitermo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TERMO")
@Data
public class Termo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descricao;
    private String versao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataModificacao;




    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }
}
