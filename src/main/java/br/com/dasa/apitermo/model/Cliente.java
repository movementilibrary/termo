package br.com.dasa.apitermo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CLIENTE")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String mdmId;
}
