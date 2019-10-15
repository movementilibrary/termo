package br.com.dasa.api.termo.repository;

import br.com.dasa.api.termo.entity.AceiteTermo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AceiteRepository extends JpaRepository<AceiteTermo, Long> {



}
