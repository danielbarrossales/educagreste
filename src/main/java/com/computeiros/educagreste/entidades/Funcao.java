package com.computeiros.educagreste.entidades;

import javax.persistence.*;

@Entity
@Table(name = "funcao")
public class Funcao {
    public Funcao(String funcao){
        this.funcao = funcao;
    }
    public Funcao(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "funcao_id")
    private int id;
    @Column(name = "funcao")
    private String funcao;

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}