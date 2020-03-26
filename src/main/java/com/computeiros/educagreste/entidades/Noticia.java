package com.computeiros.educagreste.entidades;

import javax.persistence.*;
import java.util.*;

@Entity(name="noticia")
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;

    private String conteudo;

    @ManyToMany(cascade = CascadeType.MERGE, targetEntity = Funcao.class, fetch = FetchType.EAGER)
    @JoinTable(name = "noticia_categoria",joinColumns = @JoinColumn(name = "noticia_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private Set<Categoria> categorias = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }
}
