package br.com.dasa.api.termo.repository;

import br.com.dasa.api.termo.enumeration.StatusTermUse;
import org.springframework.data.repository.CrudRepository;

import br.com.dasa.api.termo.entity.TermOfUser;

import java.util.Optional;

public interface TermOfUserRepository extends CrudRepository<TermOfUser, Long> {

    Optional<TermOfUser> findByStatus(StatusTermUse status);
    
}
