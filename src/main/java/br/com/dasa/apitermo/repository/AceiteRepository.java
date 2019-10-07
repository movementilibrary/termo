package br.com.dasa.apitermo.repository;

import br.com.dasa.apitermo.model.AceiteTermo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AceiteRepository extends CrudRepository<AceiteTermo, Long> {



}
