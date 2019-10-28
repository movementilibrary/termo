package br.com.dasa.api.termo.dao;

import java.util.HashSet;
import java.util.LinkedList;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.dasa.api.termo.entity.AceiteTermo;
import io.micrometer.core.instrument.util.StringUtils;

@Repository
public class AceiteDAOImpl extends BaseDAO implements AceiteDAO{

	@Override
	public boolean usuarioRespondeuAoTermo(String mdmId, String cip, Long termoId) {
		
		HashSet<ParametroQuery> parametros = new HashSet<>();
		StringBuilder sql = createQueryRespondeuAoTermo(mdmId, cip, termoId, parametros); 
		Query query = em.createQuery(sql.toString()); 
		parametros.forEach(p -> query.setParameter(p.getKey(), p.getValue()));
		LinkedList<AceiteTermo> lista = new LinkedList<>(query.getResultList()); 
		
		if(lista.isEmpty()) {
			return false; 
		}
		
		return lista.getFirst().getRespostaCliente(); 
	}

	private StringBuilder createQueryRespondeuAoTermo(String mdmId, String cip, Long termoId,
			HashSet<ParametroQuery> parametros) {
		StringBuilder jpql = new StringBuilder(); 
		 
		jpql.append("select a from AceiteTermo a "); 
		
		if(!StringUtils.isEmpty(mdmId)) {
			addAnd(jpql);
			jpql.append(" a.mdmIdCliente = :mdmId "); 
			parametros.add(new ParametroQuery("mdmId", mdmId)); 
		}
		
		if(!StringUtils.isEmpty(cip)) {
			addAnd(jpql);
			jpql.append(" a.cip = :cip ");
			parametros.add(new ParametroQuery("cip", cip)); 
		}
		
		if(termoId != null && termoId > 0l) {
			addAnd(jpql);
			jpql.append(" a.termOfUser.id = :termoId ");
			parametros.add(new ParametroQuery("termoId", termoId)); 
		}
		
		jpql.append(" order by a.dataAceite desc ");
		return jpql;
	}

}
