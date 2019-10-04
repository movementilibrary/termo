package br.com.dasa.apitermo.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class AceiteTermo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date dataAceite;

    private Boolean respotaCliente;

    @ManyToOne()
    private Termo termo;

    @ManyToOne
    private Cliente cliente;


}
