package com.testePeriny.service;

import com.testePeriny.entity.Pessoa;
import com.testePeriny.entity.Tarefa;
import com.testePeriny.repository.PessoaRepository;
import com.testePeriny.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    public Pessoa adicionarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa alterarPessoa(Long id, Pessoa pessoa) {
        pessoa.setId(id);
        return pessoaRepository.save(pessoa);
    }

    public void removerPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }

    public List<Pessoa> listarPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        for (Pessoa pessoa : pessoas) {
            int totalHoras = pessoa.getTarefas().stream()
                    .filter(tarefa -> tarefa.getPessoa() != null)
                    .mapToInt(Tarefa::getDuracao)
                    .sum();
            pessoa.setTotalHorasGastas(totalHoras); 
        }
        return pessoas;
    }

    public List<Pessoa> buscarPessoasPorNomeEPeriodo(String nome, String periodo) {
        List<Pessoa> pessoas = pessoaRepository.findByNome(nome);
        for (Pessoa pessoa : pessoas) {
            double mediaHoras = pessoa.getTarefas().stream()
                    .mapToInt(Tarefa::getDuracao)
                    .average()
                    .orElse(0);
            pessoa.setMediaHorasGastas(mediaHoras); 
        }
        return pessoas;
    }
}
