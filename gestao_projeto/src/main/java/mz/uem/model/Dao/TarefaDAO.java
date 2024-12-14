package mz.uem.model.Dao;

import mz.uem.model.model.Tarefa;
import mz.uem.model.model.Estado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    private Connection connection;

    // Construtor que inicializa a conexão
    public TarefaDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para salvar uma tarefa
    public void save(Tarefa tarefa) throws SQLException {
        String sql = "INSERT INTO tarefa (projetoId, nome, descricao, dataInicio, dataFim, responsavel, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tarefa.getProjetoId());
            stmt.setString(2, tarefa.getNome());
            stmt.setString(3, tarefa.getDescricao());
            stmt.setDate(4, Date.valueOf(tarefa.getDataInicio()));
            stmt.setDate(5, Date.valueOf(tarefa.getDataFim()));
            stmt.setInt(6, tarefa.getResponsavel());
            stmt.setString(7, tarefa.getEstado().toString());  // Estado é convertido para String

            stmt.executeUpdate();
        }
    }

    // Método para atualizar uma tarefa
    public void update(Tarefa tarefa) throws SQLException {
        String sql = "UPDATE tarefa SET projetoId = ?, nome = ?, descricao = ?, dataInicio = ?, dataFim = ?, "
                + "responsavel = ?, estado = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tarefa.getProjetoId());
            stmt.setString(2, tarefa.getNome());
            stmt.setString(3, tarefa.getDescricao());
            stmt.setDate(4, Date.valueOf(tarefa.getDataInicio()));
            stmt.setDate(5, Date.valueOf(tarefa.getDataFim()));
            stmt.setInt(6, tarefa.getResponsavel());
            stmt.setString(7, tarefa.getEstado().toString());  // Estado é convertido para String
            stmt.setInt(8, tarefa.getId());

            stmt.executeUpdate();
        }
    }

    // Método para buscar todas as tarefas
    public List<Tarefa> findAll() throws SQLException {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setProjetoId(rs.getInt("projetoId"));
                tarefa.setNome(rs.getString("nome"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setDataInicio(rs.getDate("dataInicio").toLocalDate());
                tarefa.setDataFim(rs.getDate("dataFim").toLocalDate());
                tarefa.setResponsavel(rs.getInt("responsavel"));
                tarefa.setEstado(Estado.valueOf(rs.getString("estado")));  // Convertendo de String para Enum

                tarefas.add(tarefa);
            }
        }

        return tarefas;
    }

    // Método para buscar uma tarefa por ID
    public Tarefa findById(int id) throws SQLException {
        String sql = "SELECT * FROM tarefa WHERE id = ?";
        Tarefa tarefa = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tarefa = new Tarefa();
                    tarefa.setId(rs.getInt("id"));
                    tarefa.setProjetoId(rs.getInt("projeto_id"));
                    tarefa.setNome(rs.getString("nome"));
                    tarefa.setDescricao(rs.getString("descricao"));
                    tarefa.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                    tarefa.setDataFim(rs.getDate("data_fim").toLocalDate());
                    tarefa.setResponsavel(rs.getInt("responsavel"));
                    tarefa.setEstado(Estado.valueOf(rs.getString("estado")));  // Convertendo de String para Enum
                }
            }
        }

        return tarefa;
    }

    // Método para excluir uma tarefa
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM tarefa WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Tarefa> buscarTarefasPorProjeto(int projetoId) {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa WHERE projetoId = ?";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
    
            stmt.setInt(1, projetoId);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setProjetoId(rs.getInt("projetoId"));
                tarefa.setNome(rs.getString("nome"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setDataInicio(rs.getDate("dataInicio").toLocalDate());
                tarefa.setDataFim(rs.getDate("dataFim").toLocalDate());
                tarefa.setResponsavel(rs.getInt("responsavel"));
                tarefa.setEstado(Estado.valueOf(rs.getString("estado")));
    
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return tarefas;
    }
    
}
