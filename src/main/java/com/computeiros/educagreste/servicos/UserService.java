package com.computeiros.educagreste.servicos;

import com.computeiros.educagreste.dtos.CadastroUsuarioDto;
import com.computeiros.educagreste.entidades.Funcao;
import com.computeiros.educagreste.entidades.UserEntity;
import com.computeiros.educagreste.repositories.RepositorioFuncao;
import com.computeiros.educagreste.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RepositorioFuncao repositorioFuncao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean isEmailOcupado(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public CadastroUsuarioDto save(CadastroUsuarioDto usuarioDto) {
        var ue = new UserEntity();
        ue.setNome(usuarioDto.getNome());
        ue.setEmail(usuarioDto.getEmail());
        ue.setSenha(usuarioDto.getSenha());
        ue.setSobrenome(usuarioDto.getSobrenome());
        ue.setSenha(passwordEncoder.encode(ue.getSenha()));
        ue.setFuncoes(new HashSet<Funcao>(Arrays.asList(repositorioFuncao.findByFuncao("USUARIO"))));
        ue = userRepository.save(ue);
        usuarioDto.setSenha(ue.getSenha());
        return usuarioDto;
    }

    public UserEntity buscarUsuarioPorEmail(String email){
        return userRepository.findByEmail(email);
    }

}