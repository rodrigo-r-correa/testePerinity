package com.testePeriny.controller;

import com.testePeriny.entity.Pessoa;
import com.testePeriny.entity.Tarefa;
import com.testePeriny.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public Tarefa adicionarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.adicionarTarefa(tarefa);
    }

    @PutMapping("/alocar/{id}")
    public Tarefa alocarPessoaNaTarefa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return tarefaService.alocarPessoaNaTarefa(id, pessoa);
    }

    @PutMapping("/finalizar/{id}")
    public Tarefa finalizarTarefa(@PathVariable Long id) {
        return tarefaService.finalizarTarefa(id);
    }

    @GetMapping("/pendentes")
    public List<Tarefa> listarTarefasPendentes() {
        return tarefaService.listarTarefasPendentes();
    }
}
