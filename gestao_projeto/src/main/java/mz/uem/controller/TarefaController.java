package mz.uem.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import mz.uem.model.Dao.Conexao;
import mz.uem.model.Dao.ProjetoDAO;
import mz.uem.model.Dao.ResponsavelDAO;
import mz.uem.model.Dao.TarefaDAO;
import mz.uem.model.model.Estado;
import mz.uem.model.model.Projeto;
import mz.uem.model.model.Tarefa;
import mz.uem.relatorio.RelatorioProjeto;
import mz.uem.model.model.Responsavel;

public class TarefaController {
    private Tarefa tarefa;
    private TarefaDAO tarefaDAO;
    private ProjetoDAO projetoDAO;
    private ResponsavelDAO responsavelDAO;
    public TarefaController(){
        try{
            tarefaDAO=new TarefaDAO(Conexao.getConnection());
            projetoDAO=new ProjetoDAO(Conexao.getConnection());
            responsavelDAO=new ResponsavelDAO(Conexao.getConnection());
        }catch(Exception e){
            System.out.println("Problema de conexao cm a BD, isso no controller."+e.getStackTrace());
        }
    }

    public void salvarTarefa(int projetoId,String nome,String descricao,LocalDate dataInicio,LocalDate dataFim,int responsavel,Estado estado){
        tarefa=new Tarefa(projetoId,nome,descricao,dataInicio, dataFim,responsavel,estado);
        try {
            tarefaDAO.save(tarefa);
            System.out.println("Tarefa salva");
        } catch (SQLException e) {
           System.out.println("Problema no metodo salvarTarefa do controller.");
           e.printStackTrace();
        }
    }
    
    public List<Projeto> getProjetos() throws SQLException{
        return projetoDAO.buscarTodos();
    }

    public List<Responsavel> getResponsavel() throws SQLException{
        return responsavelDAO.buscarTodos();
    }

    public List<Tarefa> buscarTarefasPorProjeto(int projetoId){
        return tarefaDAO.buscarTarefasPorProjeto(projetoId);
    }

    public void gerarRelatorio(Projeto projeto,String nome){
        new RelatorioProjeto(projeto,nome );
    }
}
