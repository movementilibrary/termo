package br.com.dasa.api.termo.dao;

import java.util.Optional;

import br.com.dasa.api.termo.entity.TermOfUser;

public interface TermoDAO {

	Optional<TermOfUser> buscarUltimoTermoObrigatorio(); 
}
