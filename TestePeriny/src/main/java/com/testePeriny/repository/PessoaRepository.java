package com.testePeriny.repository;

import com.testePeriny.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findByNome(String nome);
    
    @Query("SELECT p.departamento, COUNT(p), (SELECT COUNT(t) FROM Tarefa t WHERE t.departamento = p.departamento) " +
           "FROM Pessoa p GROUP BY p.departamento")
    List<Object[]> countPessoasPorDepartamento();
}
