package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.entity.VersionTerm;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface VersionTermService {

    VersionTerm saveNewVersion(VersionTerm versionTerm);

    VersionTerm findById(Integer id);

}
