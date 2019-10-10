package br.com.dasa.api.termo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import br.com.dasa.api.termo.service.TermOfUseService;

@Service
public class TermOfUseServiceImpl implements TermOfUseService {

	@Autowired
	TermOfUserRepository termOfUserRepository;

	@Override
	public Optional<TermOfUser> findById(long id) {
		return termOfUserRepository.findById(id);
	}

	@Override
	public TermOfUser save(TermOfUser entity) {
		return termOfUserRepository.save(entity);
	}
	
}
