package br.com.dasa.apitermo.repository;

import br.com.dasa.apitermo.model.AceiteTermo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AceiteRepository extends JpaRepository<AceiteTermo, Long> {



}
