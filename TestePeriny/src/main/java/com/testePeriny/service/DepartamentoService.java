package com.testePeriny.service;

import com.testePeriny.repository.PessoaRepository;
import com.testePeriny.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Object[]> listarDepartamentos() {
        return pessoaRepository.countPessoasPorDepartamento();
    }
}
