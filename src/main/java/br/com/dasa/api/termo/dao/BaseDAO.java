package br.com.dasa.api.termo.dao;

import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class BaseDAO {

	@PersistenceContext
	protected EntityManager em;
	
	protected void addAnd(StringBuilder queryStr) {
		if (queryStr.toString().toUpperCase().contains(" WHERE ")) {
			queryStr.append(" AND ");
		} else {
			queryStr.append(" WHERE ");
		}
	}
	
	
	protected void setarParametros(HashSet<ParametroQuery> parametros, Query query) {
		parametros.forEach(p -> query.setParameter(p.getKey(), p.getValue()));
	}
}
