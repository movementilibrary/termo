package br.com.dasa.api.termo.repository;

import br.com.dasa.api.termo.entity.TermOfUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermOfUserRepository extends CrudRepository<TermOfUser, Long> {



}
