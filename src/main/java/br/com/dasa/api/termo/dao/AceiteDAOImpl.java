package br.com.dasa.api.termo.dao;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.dasa.api.termo.entity.AceiteTermo;
import io.micrometer.core.instrument.util.StringUtils;

@Repository
public class AceiteDAOImpl extends BaseDAO implements AceiteDAO{

	@Override
	public boolean usuarioRespondeuAoTermo(String mdmId, Integer cip, List<Long> termosId) {
		
		HashSet<ParametroQuery> parametros = new HashSet<>();
		StringBuilder sql = createQueryRespondeuAoTermo(mdmId, cip, termosId, parametros); 
		Query query = em.createQuery(sql.toString()); 
		parametros.forEach(p -> query.setParameter(p.getKey(), p.getValue()));
		LinkedList<AceiteTermo> lista = new LinkedList<>(query.getResultList()); 
		
		if(lista.isEmpty()) {
			return false; 
		}
		
		return lista.getFirst().getRespostaCliente(); 
	}

	private StringBuilder createQueryRespondeuAoTermo(String mdmId, Integer cip, List<Long> termosId,
			HashSet<ParametroQuery> parametros) {
		StringBuilder jpql = new StringBuilder(); 
		 
		jpql.append("select a from AceiteTermo a "); 
		
		if(!StringUtils.isEmpty(mdmId)) {
			addAnd(jpql);
			jpql.append(" a.mdmIdCliente = :mdmId "); 
			parametros.add(new ParametroQuery("mdmId", mdmId)); 
		}
		
		if(cip != null && cip > 0) {
			addAnd(jpql);
			jpql.append(" a.cip = :cip ");
			parametros.add(new ParametroQuery("cip", cip)); 
		}
		
		if(termosId != null && !termosId.isEmpty()) {
			addAnd(jpql);
			jpql.append(" a.termOfUser.id in (:termoId)");
			parametros.add(new ParametroQuery("termoId", termosId)); 
		}
		
		jpql.append(" order by a.dataAceite desc ");
		return jpql;
	}

}
