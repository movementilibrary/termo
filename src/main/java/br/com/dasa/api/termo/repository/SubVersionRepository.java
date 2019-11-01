package br.com.dasa.api.termo.repository;

import br.com.dasa.api.termo.entity.SubVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubVersionRepository extends JpaRepository <SubVersion, Integer> {
}
