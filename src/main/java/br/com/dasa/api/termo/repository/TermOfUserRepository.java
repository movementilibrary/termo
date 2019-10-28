package br.com.dasa.api.termo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dasa.api.termo.dao.TermoDAO;
import br.com.dasa.api.termo.entity.TermOfUser;

@Repository
public interface TermOfUserRepository extends JpaRepository<TermOfUser, Long>, TermoDAO {



}
