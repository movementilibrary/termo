package br.com.dasa.api.termo.exceptions;

import br.com.dasa.api.termo.entity.AceiteTermo;

public class ValidaExceptions {

    private ValidaExceptions(){}


    public static void validaAceiteId(AceiteTermo termo){

    }

    public static void validaAceiteId(Long idTermo) {
        if (idTermo == null){
            throw new AceiteExceptions("idTermo");
        }
    }
}
