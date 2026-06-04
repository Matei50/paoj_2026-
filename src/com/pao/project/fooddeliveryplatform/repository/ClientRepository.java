package com.pao.project.fooddeliveryplatform.repository;

import com.pao.project.fooddeliveryplatform.model.Adresa;
import com.pao.project.fooddeliveryplatform.model.Client;
import com.pao.project.fooddeliveryplatform.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements Repository<Client, Integer> {

    @Override
    public void save(Client client) {
        String sql = "INSERT INTO Clienti (nume, parola, adresa_oras, adresa_strada, adresa_numar) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, client.getNume());
            pstmt.setString(2, client.getParola());
            if (client.getAdresa() != null) {
                pstmt.setString(3, client.getAdresa().getOras());
                pstmt.setString(4, client.getAdresa().getStrada());
                pstmt.setInt(5, client.getAdresa().getNumar());
            } else {
                pstmt.setNull(3, Types.VARCHAR);
                pstmt.setNull(4, Types.VARCHAR);
                pstmt.setNull(5, Types.INTEGER);
            }
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Client> findById(Integer id) {
        String sql = "SELECT * FROM Clienti WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Adresa adresa = new Adresa(rs.getString("adresa_oras"), rs.getString("adresa_strada"), rs.getInt("adresa_numar"));
                    Client client = new Client(rs.getString("nume"), rs.getString("parola"), adresa);
                    client.setId(rs.getInt("id"));
                    return Optional.of(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    public Optional<Client> findByNume(String nume) {
        String sql = "SELECT * FROM Clienti WHERE nume = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nume);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Adresa adresa = new Adresa(rs.getString("adresa_oras"), rs.getString("adresa_strada"), rs.getInt("adresa_numar"));
                    Client client = new Client(rs.getString("nume"), rs.getString("parola"), adresa);
                    client.setId(rs.getInt("id"));
                    return Optional.of(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Client> findAll() {
        List<Client> clienti = new ArrayList<>();
        String sql = "SELECT * FROM Clienti";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Adresa adresa = new Adresa(rs.getString("adresa_oras"), rs.getString("adresa_strada"), rs.getInt("adresa_numar"));
                Client client = new Client(rs.getString("nume"), rs.getString("parola"), adresa);
                client.setId(rs.getInt("id"));
                clienti.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienti;
    }

    @Override
    public void update(Client client) {
        String sql = "UPDATE Clienti SET nume = ?, parola = ?, adresa_oras = ?, adresa_strada = ?, adresa_numar = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, client.getNume());
            pstmt.setString(2, client.getParola());
            if (client.getAdresa() != null) {
                pstmt.setString(3, client.getAdresa().getOras());
                pstmt.setString(4, client.getAdresa().getStrada());
                pstmt.setInt(5, client.getAdresa().getNumar());
            } else {
                pstmt.setNull(3, Types.VARCHAR);
                pstmt.setNull(4, Types.VARCHAR);
                pstmt.setNull(5, Types.INTEGER);
            }
            pstmt.setInt(6, client.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Clienti WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
