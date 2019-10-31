package br.com.dasa.api.termo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dasa.api.termo.dao.AceiteDAO;
import br.com.dasa.api.termo.entity.AceiteTermo;

@Repository
public interface AceiteRepository extends JpaRepository<AceiteTermo, Long>, AceiteDAO {

}
