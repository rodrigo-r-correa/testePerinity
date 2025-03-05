package com.testePeriny.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String departamento;

    @OneToMany(mappedBy = "pessoa")
    private List<Tarefa> tarefas;

    @Transient
    private int totalHorasGastas;

    @Transient
    private double mediaHorasGastas;

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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public int getTotalHorasGastas() {
        return totalHorasGastas;
    }

    public void setTotalHorasGastas(int totalHorasGastas) {
        this.totalHorasGastas = totalHorasGastas;
    }

    public double getMediaHorasGastas() {
        return mediaHorasGastas;
    }

    public void setMediaHorasGastas(double mediaHorasGastas) {
        this.mediaHorasGastas = mediaHorasGastas;
    }
}
