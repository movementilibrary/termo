package br.com.dasa.api.termo.repository;

import br.com.dasa.api.termo.entity.VersionTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionTermRepository extends JpaRepository<VersionTerm, Integer> {


}
