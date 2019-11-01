package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.entity.SubVersion;
import br.com.dasa.api.termo.repository.SubVersionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubVersionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubVersionService.class);

    @Autowired
    private SubVersionRepository subVersionRepository;

    /**
     * Metodo responsável por criar nova sub versao
     *
     * @param subVersion
     * @return
     */
    public SubVersion saveNewSubVersion(SubVersion subVersion) {
        SubVersion newSubVersion = null;
        try {
            LOGGER.info("criando subVersao");
            newSubVersion = subVersionRepository.save(subVersion);
        } catch (Exception e) {
            LOGGER.error("Não foi possivel salvar subVersao {} ", e.getMessage());
        }
        LOGGER.info("subVersao criada com sucesso");
        return newSubVersion;
    }

    /**
     * Metodo responsavel por atualizar subversao, metodo está sendo chamado quando uma nova versão for criada
     * @param subVersion
     */
    public void updateSubVersion(SubVersion subVersion) {
        LOGGER.info("Iniciando tabela subVersão");
        subVersion.setSubVersion(0);
        saveNewSubVersion(subVersion);
    }

    /**
     * Metodo responsável por buscar subVersao pelo Id
     * @param id
     * @return
     */
    public SubVersion findById(Integer id) {
        LOGGER.info("Buscando subversao pelo id {} ", id);
        Optional<SubVersion> currentSubVersion = null;
        try {
            currentSubVersion = subVersionRepository.findById(id);
        } catch (Exception e) {
            LOGGER.error("Não foi possivel buscar subVersao pelo id {} ", id, e.getMessage());
        }
        return currentSubVersion.orElse(null);
    }
}
