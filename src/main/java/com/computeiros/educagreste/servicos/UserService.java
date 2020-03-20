package com.computeiros.educagreste.servicos;

import com.computeiros.educagreste.dtos.CadastroUsuarioDto;
import com.computeiros.educagreste.entidades.UserEntity;
import com.computeiros.educagreste.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean emailOcupied(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public CadastroUsuarioDto save(CadastroUsuarioDto usuarioDto) {
        var ue = new UserEntity();
        ue.setNome(usuarioDto.getNome());
        ue.setEmail(usuarioDto.getEmail());
        ue.setSenha(usuarioDto.getSenha());
        ue.setSobrenome(usuarioDto.getSobrenome());
        ue.setSenha(passwordEncoder.encode(ue.getSenha()));
        ue = userRepository.save(ue);
        usuarioDto.setSenha(ue.getSenha());
        return (ue != null) ? usuarioDto : null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}