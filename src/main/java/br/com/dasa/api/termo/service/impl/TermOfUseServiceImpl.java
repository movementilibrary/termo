package br.com.dasa.api.termo.service.impl;

import java.util.Date;
import java.util.Optional;

import br.com.dasa.api.termo.entity.SubVersion;
import br.com.dasa.api.termo.entity.VersionTerm;
import br.com.dasa.api.termo.enumeration.StatusTermUse;
import br.com.dasa.api.termo.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import br.com.dasa.api.termo.service.TermOfUseService;

@Service
public class TermOfUseServiceImpl implements TermOfUseService {

    @Autowired
    private TermOfUserRepository termOfUserRepository;

    @Autowired
    private VersionTermImpl versionTermImpl;

    @Autowired
    private SubVersionImpl subVersionImpl;


    @Override
    public TermOfUser save(TermOfUser termOfUser) {
        TermOfUser newTermOfUser = null;
        try {
            termOfUser.setCurrentDate(new Date());
            newTermOfUser = termOfUserRepository.save(termOfUser);
        } catch (Exception e) {
            e.getMessage();
        }
        return newTermOfUser;
    }

    @Override
    public TermOfUser checkFlagIsMarked(TermOfUser termOfUser) {
        String newVersion = null;

        if (termOfUser.isFlag() == true) {
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
        VersionTerm currenteVersion = versionTermImpl.findById(1);
        if (currenteVersion == null) {
            newVersion = versionTermImpl.saveNewVersion(new VersionTerm(1));
        } else {
            inativeCurrentTermOfUser();
            currenteVersion.setVersion(currenteVersion.getVersion() + 1);
            newVersion = versionTermImpl.saveNewVersion(currenteVersion);

            SubVersion currentSubVersion = subVersionImpl.findById(1);
            if (currentSubVersion != null) {
                subVersionImpl.updateSubVersion(currentSubVersion);
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
        SubVersion currentSubVersion = subVersionImpl.findById(1);
        VersionTerm currentVersion = versionTermImpl.findById(1);

        if(currentVersion==null){
            throw new ResourceNotFoundException("Ai não amigão, criar subVersão sem umva versão é mancada");
        }
        inativeCurrentTermOfUser();
        if (currentSubVersion == null) {
            newSubVersion = subVersionImpl.saveNewSubVersion(new SubVersion(1));
        } else {
            currentSubVersion.setSubVersion(currentSubVersion.getSubVersion() + 1);
            newSubVersion = subVersionImpl.saveNewSubVersion(currentSubVersion);
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
        Optional<TermOfUser> currentTermOfUser = null;
        try {
            currentTermOfUser = termOfUserRepository.findById(id);
        } catch (Exception e) {
            e.getMessage();
        }
        return currentTermOfUser;
    }

    /**
     * Metodo responsável por inativar Termo de Usuario atual
     */
    public void inativeCurrentTermOfUser() {
        Optional<TermOfUser> currentTermOfUser = null;
        try {
            currentTermOfUser = termOfUserRepository.findByStatus(StatusTermUse.ACTIVE);
            if (currentTermOfUser.isPresent()) {
                currentTermOfUser.get().setStatus(StatusTermUse.INACTIVE);
                save(currentTermOfUser.get());
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }


}
