package com.computeiros.educagreste.servicos;

import com.computeiros.educagreste.entidades.Funcao;
import com.computeiros.educagreste.entidades.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ServicoDetalhesUsuario implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var user = userService.buscarUsuarioPorEmail(s);
        for(var func : user.getFuncoes()){
            System.out.println(func.getFuncao());
        }
        return criarUsuarioParaAutenticacao(user, getFuncoesUsuario(user.getFuncoes()));
    }

    private List<GrantedAuthority> getFuncoesUsuario(Set<Funcao> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Funcao role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getFuncao()));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails criarUsuarioParaAutenticacao(UserEntity user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getSenha(),
                true, true, true, true, authorities);
    }
}
