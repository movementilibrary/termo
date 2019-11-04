package br.com.dasa.api.termo.repository;


import br.com.dasa.api.termo.enumeration.StatusTermUse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.dasa.api.termo.dao.TermoDAO;
import br.com.dasa.api.termo.entity.TermOfUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface TermOfUserRepository extends JpaRepository<TermOfUser, Long>, TermoDAO {

    Optional<TermOfUser> findFirstByLoginUser(String user);

    List<TermOfUser> findByStatus(StatusTermUse status);


}
