package br.com.dasa.api.termo.dao;

import java.util.List;

public interface AceiteDAO {

	boolean usuarioRespondeuAoTermo(String mdmId, Long cip, List<Long> termosId);
	
}
