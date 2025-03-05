package com.testePeriny.controller;

import com.testePeriny.entity.Pessoa;
import com.testePeriny.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public Pessoa adicionarPessoa(@RequestBody Pessoa pessoa) {
        return pessoaService.adicionarPessoa(pessoa);
    }


    @PutMapping("/{id}")
    public Pessoa alterarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return pessoaService.alterarPessoa(id, pessoa);
    }

    @DeleteMapping("/{id}")
    public void removerPessoa(@PathVariable Long id) {
        pessoaService.removerPessoa(id);
    }

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoaService.listarPessoas();
    }

    @GetMapping("/gastos")
    public List<Pessoa> buscarPessoasPorNomeEPeriodo(@RequestParam String nome, @RequestParam String periodo) {
        return pessoaService.buscarPessoasPorNomeEPeriodo(nome, periodo);
    }
}