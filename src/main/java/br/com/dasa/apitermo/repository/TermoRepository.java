package br.com.dasa.apitermo.repository;

import br.com.dasa.apitermo.model.Termo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermoRepository extends CrudRepository<Termo, Long> {


}
