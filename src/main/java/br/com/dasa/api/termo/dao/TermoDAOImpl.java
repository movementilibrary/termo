package br.com.dasa.api.termo.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.enumeration.StatusTermUse;

@Repository
public class TermoDAOImpl extends BaseDAO implements TermoDAO{

	
	@Override
	public Optional<TermOfUser> buscarUltimoTermoObrigatorio() {
	
		StringBuilder jpql = new StringBuilder(); 
		
		jpql.append("select t from TermOfUser t where t.flagAtualizacao = false and t.status = :status order by t.id desc "); 
		
		Query query = em.createQuery(jpql.toString()); 
		
		query.setParameter("status", StatusTermUse.ACTIVE); 
		
		List<TermOfUser> lista = query.getResultList(); 
		
		if(lista != null && !lista.isEmpty()) {
			return Optional.of(lista.get(0)); 
		}
		
		return Optional.empty();
	}

	@Override
	public List<TermOfUser> buscarTermosUltimosTermosNaoObrigatorios(Long idUltimoTermo) {
		
		StringBuilder jpql = new StringBuilder(); 
		
		jpql.append("select t from TermOfUser t where t.flagAtualizacao = true and t.status = :status and t.id > :idUltimoTermo order by t.id desc ");
		
		Query query = em.createQuery(jpql.toString()); 
		
		query.setParameter("status", StatusTermUse.ACTIVE);
		
		query.setParameter("idUltimoTermo", idUltimoTermo);
		
		return query.getResultList(); 
	}

}
