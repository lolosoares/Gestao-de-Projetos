package mz.uem.model.Dao;

import mz.uem.model.model.Responsavel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResponsavelDAO {
    private Connection connection;

    public ResponsavelDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um novo responsável
    public void inserir(Responsavel responsavel) throws SQLException {
        String sql = "INSERT INTO responsavel (nome, email, telefone, cargo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, responsavel.getNome());
            stmt.setString(2, responsavel.getEmail());
            stmt.setString(3, responsavel.getTelefone());
            stmt.setString(4, responsavel.getCargo());

            stmt.executeUpdate();

            // Recupera o ID gerado automaticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    responsavel.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Método para buscar todos os responsáveis
    public List<Responsavel> buscarTodos() throws SQLException {
        List<Responsavel> responsaveis = new ArrayList<>();
        String sql = "SELECT * FROM responsavel";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Responsavel responsavel = new Responsavel();
                responsavel.setId(rs.getInt("id"));
                responsavel.setNome(rs.getString("nome"));
                responsavel.setEmail(rs.getString("email"));
                responsavel.setTelefone(rs.getString("telefone"));
                responsavel.setCargo(rs.getString("cargo"));

                responsaveis.add(responsavel);
            }
        }

        return responsaveis;
    }

    // Método para buscar um responsável por ID
    public Responsavel buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM responsavel WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Responsavel responsavel = new Responsavel();
                    responsavel.setId(rs.getInt("id"));
                    responsavel.setNome(rs.getString("nome"));
                    responsavel.setEmail(rs.getString("email"));
                    responsavel.setTelefone(rs.getString("telefone"));
                    responsavel.setCargo(rs.getString("cargo"));

                    return responsavel;
                }
            }
        }

        return null; // Retorna null se o responsável não for encontrado
    }

    // Método para atualizar os dados de um responsável
    public void atualizar(Responsavel responsavel) throws SQLException {
        String sql = "UPDATE responsavel SET nome = ?, email = ?, telefone = ?, cargo = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, responsavel.getNome());
            stmt.setString(2, responsavel.getEmail());
            stmt.setString(3, responsavel.getTelefone());
            stmt.setString(4, responsavel.getCargo());
            stmt.setInt(5, responsavel.getId());

            stmt.executeUpdate();
        }
    }

    // Método para excluir um responsável por ID
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM responsavel WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
