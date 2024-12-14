package mz.uem.model.Dao;

import mz.uem.model.model.Projeto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private Connection connection;

    public ProjetoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um novo projeto no banco de dados
    public void inserir(Projeto projeto) throws SQLException {
        String sql = "INSERT INTO projeto (nome, descricao, dataInicio, dataFim, responsavel) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setDate(3, Date.valueOf(projeto.getDataInicio()));
            stmt.setDate(4, Date.valueOf(projeto.getDataFim()));
            stmt.setInt(5, projeto.getResponsavel());

            stmt.executeUpdate();

            // Recupera o ID gerado automaticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    projeto.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Método para buscar todos os projetos
    public List<Projeto> buscarTodos() throws SQLException {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM projeto";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setDataInicio(rs.getDate("dataInicio").toLocalDate());
                projeto.setDataFim(rs.getDate("dataFim").toLocalDate());
                projeto.setResponsavel(rs.getInt("responsavel"));

                projetos.add(projeto);
            }
        }

        return projetos;
    }

    // Método para buscar um projeto por ID
    public Projeto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM projeto WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Projeto projeto = new Projeto();
                    projeto.setId(rs.getInt("id"));
                    projeto.setNome(rs.getString("nome"));
                    projeto.setDescricao(rs.getString("descricao"));
                    projeto.setDataInicio(rs.getDate("dataInicio").toLocalDate());
                    projeto.setDataFim(rs.getDate("dataFim").toLocalDate());
                    projeto.setResponsavel(rs.getInt("responsavel"));

                    return projeto;
                }
            }
        }

        return null; // Retorna null se o projeto não for encontrado
    }

    // Método para atualizar um projeto
    public void atualizar(Projeto projeto) throws SQLException {
        String sql = "UPDATE projeto SET nome = ?, descricao = ?, dataInicio = ?, dataFim = ?, responsavel = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setDate(3, Date.valueOf(projeto.getDataInicio()));
            stmt.setDate(4, Date.valueOf(projeto.getDataFim()));
            stmt.setInt(5, projeto.getResponsavel());
            stmt.setInt(6, projeto.getId());

            stmt.executeUpdate();
        }
    }

    // Método para excluir um projeto por ID
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM projeto WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
