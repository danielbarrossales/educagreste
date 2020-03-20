package com.computeiros.educagreste;

import com.computeiros.educagreste.dtos.CadastroUsuarioDto;
import com.computeiros.educagreste.servicos.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

@SpringBootTest
public class TestServicoUsuario {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void componentesInjetadosNaoSaoNulos(){
        Assert.notNull(userService, "A classe '" + userService.getClass().getName() +
                "' não foi injetada pelo sprint");
    }

    @Test
    void salvarUsuarioService() {
        var usuarioDto = new CadastroUsuarioDto();
        usuarioDto.setEmail("cicrano@algo.com");
        usuarioDto.setNome("Cicrano");
        usuarioDto.setSobrenome(("de Beltrano"));
        usuarioDto.setSenha("asdfghk");
        usuarioDto.setTermos(true);
        //Testes
        Assert.notNull(userService.save(usuarioDto), "Serviço falhou ao tentar salvar usuário");
        Assert.isTrue((userService.isEmailOcupado(usuarioDto.getEmail())),
                "UserService erroneamente afirma que o e-mail não esta sendo usado.");
        Assert.isTrue(!(userService.isEmailOcupado("asdasd@asdasdas.com")),
                "UserService erroneamente afirma que o e-mail esta sendo usado.");
    }

    @Test
    void senhaFoiEncriptografada(){
        var usuarioDto = new CadastroUsuarioDto();
        usuarioDto.setEmail("cicranoaa@algoaa.comaa");
        usuarioDto.setNome("Cicrano");
        usuarioDto.setSobrenome(("de Beltrano"));
        usuarioDto.setSenha("asdfghkddd");
        usuarioDto.setTermos(true);
        var uDto = userService.save(usuarioDto);
        usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        Assert.isTrue(usuarioDto.getSenha().equals(uDto.getSenha()),
                "Senha não foi encriptografada\nusuarioDto.senha = " +
                        passwordEncoder.encode(usuarioDto.getSenha()) + "\nuDto.senha = " + uDto.getSenha());
    }
}
