package com.computeiros.educagreste.servicos.spring.seguranca;

import com.computeiros.educagreste.servicos.ServicoDetalhesUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class ConfiguracaoSegurancaWeb extends WebSecurityConfigurerAdapter {
    @Autowired
    private ServicoDetalhesUsuario servicoDetalhesUsuario;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(servicoDetalhesUsuario)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/autenticar").permitAll()
                .antMatchers("/cadastrar").permitAll()
                .antMatchers("/usuario/**").hasAuthority("USUARIO").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/autenticar").failureUrl("/autenticar?erro=true")
                .defaultSuccessUrl("/index")
                .usernameParameter("email")
                .passwordParameter("senha")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/sair"))
                .logoutSuccessUrl("/autenticar").and().exceptionHandling()
                .accessDeniedPage("/acesso-negado");
    }
}
