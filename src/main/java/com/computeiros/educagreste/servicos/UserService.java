package com.computeiros.educagreste.servicos;

import com.computeiros.educagreste.dtos.CadastroUsuarioDto;
import com.computeiros.educagreste.entidades.UserEntity;
import com.computeiros.educagreste.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public boolean emailOcupied(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public CadastroUsuarioDto save(CadastroUsuarioDto usuarioDto) {
        var ue = new UserEntity();
        ue.setNome(usuarioDto.getNome());
        ue.setEmail(usuarioDto.getEmail());
        ue.setSenha(usuarioDto.getSenha());
        ue.setSobrenome(usuarioDto.getSobrenome());

        ue = userRepository.save(ue);
        return (ue != null) ? usuarioDto : null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}