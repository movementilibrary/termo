package br.com.dasa.api.termo.service.impl;

import br.com.dasa.api.termo.entity.SubVersion;
import br.com.dasa.api.termo.entity.VersionTerm;
import br.com.dasa.api.termo.repository.SubVersionRepository;
import br.com.dasa.api.termo.service.SubVersionTermService;
import org.springframework.beans.factory.annotation.Autowired;

public class SubVersionImpl implements SubVersionTermService {

    @Autowired
    private SubVersionRepository subVersionRepository;


    @Override
    public Integer saveNewSubVersion(SubVersion subVersion) {
        return subVersionRepository.save(subVersion).getId();
    }

    @Override
    public void updateSubVersion(SubVersion subVersion) {
        subVersion.setSubVersion(0);
        saveNewSubVersion(subVersion);
    }

    @Override
    public SubVersion findById(Integer id) {
        return null;
    }
}
