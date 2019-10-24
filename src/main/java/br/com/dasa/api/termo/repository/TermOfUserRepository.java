package br.com.dasa.api.termo.repository;

import br.com.dasa.api.termo.entity.TermOfUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermOfUserRepository extends JpaRepository<TermOfUser, Long> {



}
