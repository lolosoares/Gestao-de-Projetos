package mz.uem.model.model;

import java.time.LocalDate;

public class Tarefa {
    private int id;
    private int projetoId;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int responsavel;  // Alterado para um objeto
    private Estado estado=Estado.Pendente ;

    public Tarefa(int projetoId, String nome, String descricao, LocalDate dataInicio, LocalDate dataFim, int responsavel, Estado estado) {
        this.projetoId = projetoId;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.responsavel = responsavel;
    }
    public Tarefa(){}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getProjetoId() {
        return projetoId;
    }
    public void setProjetoId(int projetoId) {
        this.projetoId = projetoId;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    public LocalDate getDataFim() {
        return dataFim;
    }
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
    public int getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(int responsavel) {
        this.responsavel = responsavel;
    }
    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString(){
        return this.nome;
    }
    
}
