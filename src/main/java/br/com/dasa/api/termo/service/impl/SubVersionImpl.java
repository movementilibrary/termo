package br.com.dasa.api.termo.service.impl;

import br.com.dasa.api.termo.entity.SubVersion;
import br.com.dasa.api.termo.repository.SubVersionRepository;
import br.com.dasa.api.termo.service.SubVersionTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubVersionImpl implements SubVersionTermService {

    @Autowired
    private SubVersionRepository subVersionRepository;


    @Override
    public SubVersion saveNewSubVersion(SubVersion subVersion) {
        SubVersion newSubVersion = null;
        try {
         newSubVersion = subVersionRepository.save(subVersion);

        }catch (Exception e){
            e.getMessage();
        }

        return newSubVersion;
    }

    @Override
    public void updateSubVersion(SubVersion subVersion) {
        subVersion.setSubVersion(0);
        saveNewSubVersion(subVersion);
    }

    @Override
    public SubVersion findById(Integer id) {
        Optional<SubVersion> currentSubVersion = null;
        try {
            currentSubVersion = subVersionRepository.findById(id);
        }catch (Exception e){
            e.getMessage();
        }
        return currentSubVersion.orElse(null);
    }
}
