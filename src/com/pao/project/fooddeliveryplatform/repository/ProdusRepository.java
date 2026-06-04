package com.pao.project.fooddeliveryplatform.repository;

import com.pao.project.fooddeliveryplatform.model.Produs;
import com.pao.project.fooddeliveryplatform.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdusRepository implements Repository<Produs, Integer> {

    @Override
    public void save(Produs produs) {
        String sql = "INSERT INTO Produse (nume, categorie, pret, id_restaurant) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, produs.getNume());
            pstmt.setString(2, produs.getCategorie());
            pstmt.setDouble(3, produs.getPret());
            pstmt.setInt(4, produs.getIdRestaurant());
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    produs.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Produs> findById(Integer id) {
        String sql = "SELECT * FROM Produse WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Produs produs = new Produs(rs.getString("nume"), rs.getString("categorie"), rs.getDouble("pret"));
                    produs.setId(rs.getInt("id"));
                    produs.setIdRestaurant(rs.getInt("id_restaurant"));
                    return Optional.of(produs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    public List<Produs> findByRestaurantId(int idRestaurant) {
        List<Produs> produse = new ArrayList<>();
        String sql = "SELECT * FROM Produse WHERE id_restaurant = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idRestaurant);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Produs produs = new Produs(rs.getString("nume"), rs.getString("categorie"), rs.getDouble("pret"));
                    produs.setId(rs.getInt("id"));
                    produs.setIdRestaurant(rs.getInt("id_restaurant"));
                    produse.add(produs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produse;
    }

    @Override
    public List<Produs> findAll() {
        List<Produs> produse = new ArrayList<>();
        String sql = "SELECT * FROM Produse";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Produs produs = new Produs(rs.getString("nume"), rs.getString("categorie"), rs.getDouble("pret"));
                produs.setId(rs.getInt("id"));
                produs.setIdRestaurant(rs.getInt("id_restaurant"));
                produse.add(produs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produse;
    }

    @Override
    public void update(Produs produs) {
        String sql = "UPDATE Produse SET nume = ?, categorie = ?, pret = ?, id_restaurant = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, produs.getNume());
            pstmt.setString(2, produs.getCategorie());
            pstmt.setDouble(3, produs.getPret());
            pstmt.setInt(4, produs.getIdRestaurant());
            pstmt.setInt(5, produs.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Produse WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
