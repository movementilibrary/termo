package br.com.dasa.api.termo.repository;

import br.com.dasa.api.termo.dao.AceiteDAO;
import br.com.dasa.api.termo.entity.AceiteTermo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AceiteRepository extends JpaRepository<AceiteTermo, Long>, AceiteDAO {
    Optional<AceiteTermo> findById(Long id);
}
