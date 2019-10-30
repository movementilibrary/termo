package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.entity.VersionTerm;
import org.springframework.stereotype.Service;

@Service
public interface VersionTermService {

    Integer saveNewVersion(VersionTerm versionTerm);

    VersionTerm findById(Integer id);

}
