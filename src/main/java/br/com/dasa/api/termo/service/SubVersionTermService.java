package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.entity.SubVersion;

import java.util.Optional;


public interface SubVersionTermService {

    SubVersion saveNewSubVersion(SubVersion subVersion);

    void updateSubVersion(SubVersion subVersion);

    SubVersion findById(Integer id);
}
