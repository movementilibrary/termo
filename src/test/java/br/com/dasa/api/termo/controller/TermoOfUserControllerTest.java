package br.com.dasa.api.termo.controller;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.enumeration.StatusTermUse;
import br.com.dasa.api.termo.repository.TermOfUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

@ActiveProfiles(value = {"test"})
@SpringBootTest
public class TermoOfUserControllerTest {


    @Autowired
    private TermOfUserEndPoint term;

    @Autowired
    private TermOfUserRepository ofUserRepository;


    @Test
    public void test_salvaTermo() {



    }




}
