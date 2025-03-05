package com.testePeriny.service;

import com.testePeriny.entity.Pessoa;
import com.testePeriny.entity.Tarefa;
import com.testePeriny.repository.TarefaRepository;
import com.testePeriny.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tarefa adicionarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa alocarPessoaNaTarefa(Long id, Pessoa pessoa) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa n�o encontrada"));

        if (tarefa.getDepartamento().equals(pessoa.getDepartamento())) {
            tarefa.setPessoa(pessoa);
            return tarefaRepository.save(tarefa);
        } else {
            throw new RuntimeException("Departamento da pessoa n�o corresponde ao da tarefa");
        }
    }

    public Tarefa finalizarTarefa(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa n�o encontrada"));
        tarefa.setFinalizada(true);
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefasPendentes() {
        List<Tarefa> tarefasPendentes = tarefaRepository.findByPessoaIsNull();
        tarefasPendentes.sort((t1, t2) -> t1.getPrazo().compareTo(t2.getPrazo())); 
        return tarefasPendentes.stream().limit(3).collect(Collectors.toList()); 
    }
}
