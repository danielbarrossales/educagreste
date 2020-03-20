package com.computeiros.educagreste.entidades;

import javax.persistence.*;
import java.util.Set;

@Entity(name="user")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_funcao",joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "funcao_id"))
    private Set<Funcao> funcoes;

    public UserEntity() {
    }

    public UserEntity(String nome, String sobrenome, String email, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Funcao> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(Set<Funcao> funcoes) {
        this.funcoes = funcoes;
    }
}