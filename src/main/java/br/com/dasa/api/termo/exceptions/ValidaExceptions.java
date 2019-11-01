package br.com.dasa.api.termo.exceptions;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.json.AceiteTermoJson;
import br.com.dasa.api.termo.enumeration.StatusTermUse;
import br.com.dasa.api.termo.exceptions.enums.AceiteTermoEnums;

public class ValidaExceptions {

    private ValidaExceptions(){}



    public static void validaAceiteId(Long idTermo) {
        if (idTermo == null || idTermo.toString().trim().isEmpty()){
            throw new AceiteException(AceiteTermoEnums.ID_NULL);
        }
        if (idTermo <= 0){
            throw new AceiteException(AceiteTermoEnums.ID_ZERO);
        }

    }

    public static void validaTermo(AceiteTermoJson aceiteTermoJson){
        if (aceiteTermoJson == null){
            throw new AceiteException(AceiteTermoEnums.TERMO_NULLO);
        }
        if (aceiteTermoJson.getMdmId() == null || aceiteTermoJson.getMdmId().trim().isEmpty()){
            throw new AceiteException(AceiteTermoEnums.MDM_ID_NULLO);
        }
        if (aceiteTermoJson.getCip().toString().matches("[A-Z]")){
            throw new AceiteException(AceiteTermoEnums.CIP_INVALIDA);
        }


    }

    public static void validaStatus(TermOfUser termOfUser){

            if (termOfUser.getStatus().equals(StatusTermUse.INACTIVE)){
                throw  new AceiteException(AceiteTermoEnums.STATUS_INATIVO);
            }
    }



}
