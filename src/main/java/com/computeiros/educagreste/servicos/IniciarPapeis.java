package com.computeiros.educagreste.servicos;

import com.computeiros.educagreste.entidades.Funcao;
import com.computeiros.educagreste.repositories.RepositorioFuncao;
import com.computeiros.educagreste.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class IniciarPapeis {
    @Autowired
    RepositorioFuncao repositorioFuncao;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        var funcao = new Funcao("USUARIO");
        repositorioFuncao.save(funcao);
        System.out.println("hello world, I have just started up");
    }
}
