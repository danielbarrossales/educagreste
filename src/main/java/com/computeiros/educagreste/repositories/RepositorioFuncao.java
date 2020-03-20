package com.computeiros.educagreste.repositories;

import com.computeiros.educagreste.entidades.Funcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioFuncao extends JpaRepository<Funcao, Long> {
    Funcao findByFuncao(String funcao);
}
