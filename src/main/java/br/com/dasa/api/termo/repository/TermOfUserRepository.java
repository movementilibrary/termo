package br.com.dasa.api.termo.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.dasa.api.termo.entity.TermOfUser;

public interface TermOfUserRepository extends CrudRepository<TermOfUser, Long> {

    TermOfUser findById(long id);
    
    
}
