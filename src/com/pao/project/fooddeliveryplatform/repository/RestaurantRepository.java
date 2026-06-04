package com.pao.project.fooddeliveryplatform.repository;

import com.pao.project.fooddeliveryplatform.model.CategorieRestaurant;
import com.pao.project.fooddeliveryplatform.model.Restaurant;
import com.pao.project.fooddeliveryplatform.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantRepository implements Repository<Restaurant, Integer> {

    @Override
    public void save(Restaurant restaurant) {
        String sql = "INSERT INTO Restaurante (nume, program, categorie, rating) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, restaurant.getNume());
            pstmt.setString(2, restaurant.getProgram());
            pstmt.setString(3, restaurant.getCategorie() != null ? restaurant.getCategorie().name() : null);
            pstmt.setDouble(4, restaurant.getRating());
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    restaurant.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Restaurant> findById(Integer id) {
        String sql = "SELECT * FROM Restaurante WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    CategorieRestaurant cat = rs.getString("categorie") != null ? CategorieRestaurant.valueOf(rs.getString("categorie")) : null;
                    Restaurant restaurant = new Restaurant(rs.getString("nume"), rs.getString("program"), cat, rs.getDouble("rating"));
                    restaurant.setId(rs.getInt("id"));
                    return Optional.of(restaurant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    public Optional<Restaurant> findByNume(String nume) {
        String sql = "SELECT * FROM Restaurante WHERE nume = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nume);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    CategorieRestaurant cat = rs.getString("categorie") != null ? CategorieRestaurant.valueOf(rs.getString("categorie")) : null;
                    Restaurant restaurant = new Restaurant(rs.getString("nume"), rs.getString("program"), cat, rs.getDouble("rating"));
                    restaurant.setId(rs.getInt("id"));
                    return Optional.of(restaurant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Restaurant> findAll() {
        List<Restaurant> restaurante = new ArrayList<>();
        String sql = "SELECT * FROM Restaurante";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                CategorieRestaurant cat = rs.getString("categorie") != null ? CategorieRestaurant.valueOf(rs.getString("categorie")) : null;
                Restaurant restaurant = new Restaurant(rs.getString("nume"), rs.getString("program"), cat, rs.getDouble("rating"));
                restaurant.setId(rs.getInt("id"));
                restaurante.add(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurante;
    }

    @Override
    public void update(Restaurant restaurant) {
        String sql = "UPDATE Restaurante SET nume = ?, program = ?, categorie = ?, rating = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, restaurant.getNume());
            pstmt.setString(2, restaurant.getProgram());
            pstmt.setString(3, restaurant.getCategorie() != null ? restaurant.getCategorie().name() : null);
            pstmt.setDouble(4, restaurant.getRating());
            pstmt.setInt(5, restaurant.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Restaurante WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
