package br.com.dasa.api.termo.service;

import br.com.dasa.api.termo.entity.SubVersion;


public interface SubVersionTermService {

    Integer saveNewSubVersion(SubVersion subVersion);

    void updateSubVersion(SubVersion subVersion);

    SubVersion findById(Integer id);
}
