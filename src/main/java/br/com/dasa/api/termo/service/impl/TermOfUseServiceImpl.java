package br.com.dasa.api.termo.service.impl;

import br.com.dasa.api.termo.entity.SubVersion;
import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.entity.VersionTerm;
import br.com.dasa.api.termo.entity.json.TermoOfUserJson;
import br.com.dasa.api.termo.enumeration.StatusTermUse;
import br.com.dasa.api.termo.exceptions.ResourceNotFoundException;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import br.com.dasa.api.termo.service.SubVersionService;
import br.com.dasa.api.termo.service.TermOfUseService;
import br.com.dasa.api.termo.service.VersionTermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TermOfUseServiceImpl implements TermOfUseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TermOfUseServiceImpl.class);

    @Autowired
    private TermOfUserRepository termOfUserRepository;

    @Autowired
    private VersionTermService versionTermService;

    @Autowired
    private SubVersionService subVersionService;

    @Override
    public TermOfUser save(TermOfUser termOfUser) {
        TermOfUser newTermOfUser = null;
        try {
            LOGGER.info("Salvando termo do usuário");
            newTermOfUser = termOfUserRepository.save(termOfUser);
        } catch (Exception e) {
            LOGGER.error("não foi possivel salvar termo do usuário", e.getMessage());
        }
        LOGGER.info("Termo do usuário salvo com sucesso");
        return newTermOfUser;
    }

    @Override
    public TermOfUser checkFlagIsMarked(TermoOfUserJson termoOfUserJson) {
        TermOfUser termOfUser = convertTerOfUserJsonToTermOfUserJson(termoOfUserJson);
        String newVersion = null;
        if (termOfUser.isFlagAtualizacao() == true) {
            newVersion = generatedNewVersion();
        } else {
            newVersion = generatedNewSubVersion();
        }
        termOfUser.setVersion(newVersion);
        save(termOfUser);
        return termOfUser;
    }

    /**
     * Metodo responsável por vertificar se Flag is marcada, caso esteja marcada gera Versao Grande
     * caso não estaje marcada gera versão menor
     *
     * @param
     * @return
     */
    public String generatedNewVersion() {
        VersionTerm newVersion = null;
        VersionTerm currenteVersion = versionTermService.findById(1);
        if (currenteVersion == null) {
            newVersion = versionTermService.saveNewVersion(new VersionTerm(1));
        } else {
            inativeCurrentTermOfUser();
            currenteVersion.setVersion(currenteVersion.getVersion() + 1);
            newVersion = versionTermService.saveNewVersion(currenteVersion);

            SubVersion currentSubVersion = subVersionService.findById(1);
            if (currentSubVersion != null) {
                subVersionService.updateSubVersion(currentSubVersion);
            }
        }
        return "V".concat(newVersion.getVersion().toString());
    }


    /**
     * Metodo responsável por gerar nova SubVersion, subVersion será criada quando
     * flag não está marcada
     *
     * @return
     */
    public String generatedNewSubVersion() {
        SubVersion newSubVersion = null;
        SubVersion currentSubVersion = subVersionService.findById(1);
        VersionTerm currentVersion = versionTermService.findById(1);

        if (currentVersion == null) {
            throw new ResourceNotFoundException("Ai não amigão, criar su-versão sem uma versão é mancada ");
        }

        inativeCurrentTermOfUser();

        if (currentSubVersion == null) {
            newSubVersion = subVersionService.saveNewSubVersion(new SubVersion(1));
        } else {
            currentSubVersion.setSubVersion(currentSubVersion.getSubVersion() + 1);
            newSubVersion = subVersionService.saveNewSubVersion(currentSubVersion);
        }

        return "V".concat(currentVersion.getVersion().toString()).concat(".").concat(newSubVersion.getSubVersion().toString());
    }


    /**
     * Metodo Responsável por retornar TermOfUser pelo Id
     *
     * @param id
     * @return
     */
    @Override
    public Optional<TermOfUser> findById(long id) {
        LOGGER.info("Iniciando busca termo pelo id {}", id);
        Optional<TermOfUser> currentTermOfUser = null;
        try {
            currentTermOfUser = termOfUserRepository.findById(id);
        } catch (Exception e) {
            LOGGER.error("Não foi possivel buscar termo pelo id {}", id, e.getMessage());
        }
        return currentTermOfUser;
    }

    /**
     * Metodo responsável por retornar Termo pelo Status
     * @param status
     * @return
     */
    @Override
    public List<TermOfUser> findByStatus(StatusTermUse status) {
        List<TermOfUser> currentTermOfUser = null;
        try {
            LOGGER.info("Iniciando busca pelo status ");
            currentTermOfUser = termOfUserRepository.findByStatus(status);
        }catch (Exception e){
            LOGGER.error("Não foi possivel buscar termo pelo status", e.getMessage());
        }
        return currentTermOfUser;
    }

    /**
     * Metodo responsável por inativar Termo de Usuario
     */
    public void inativeCurrentTermOfUser() {
        List<TermOfUser> currentTermOfUser = null;
        LOGGER.info("Inativando termo versão anterior");
        try {
            currentTermOfUser = findByStatus(StatusTermUse.ACTIVE);
            if (currentTermOfUser != null) {
                currentTermOfUser.get(0).setStatus(StatusTermUse.INACTIVE);
                save(currentTermOfUser.get(0));
            }
        } catch (Exception e) {
            LOGGER.info("Não foi possivel encontrar termo do usuário ativo ", e.getMessage());
        }
    }

    /**
     * Metodo responsável por converter TermOfUserJson to TermOfUser
     *
     * @param termoOfUserJson
     * @return
     */
    public TermOfUser convertTerOfUserJsonToTermOfUserJson(TermoOfUserJson termoOfUserJson) {
        LOGGER.info("Convertendo TermoOfUserJson para TermoOfUser");
        TermOfUser termOfUser = new TermOfUser();
        termOfUser.setDescriptionTerm(termoOfUserJson.getDescriptionTerm());
        termOfUser.setLoginUser(termoOfUserJson.getLoginUser());
        termOfUser.setSummaryTerm(termoOfUserJson.getSummaryTerm());
        termOfUser.setFlagAtualizacao(termoOfUserJson.getFlagAtualizacao());
        termOfUser.setStatus(StatusTermUse.ACTIVE);
        return termOfUser;
    }
}

