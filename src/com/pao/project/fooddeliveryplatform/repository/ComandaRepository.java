package com.pao.project.fooddeliveryplatform.repository;

import com.pao.project.fooddeliveryplatform.model.Client;
import com.pao.project.fooddeliveryplatform.model.Comanda;
import com.pao.project.fooddeliveryplatform.model.Produs;
import com.pao.project.fooddeliveryplatform.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComandaRepository implements Repository<Comanda, Integer> {

    @Override
    public void save(Comanda comanda) {
        String insertComandaSql = "INSERT INTO Comenzi (id_client, status, total) VALUES (?, ?, ?)";
        String insertProduseSql = "INSERT INTO Comenzi_Produse (id_comanda, id_produs) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection()) {
            conn.setAutoCommit(false);


            int comandaId = -1;
            try (PreparedStatement pstmt = conn.prepareStatement(insertComandaSql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, comanda.getClient().getId());
                pstmt.setString(2, comanda.getStatus());
                pstmt.setDouble(3, comanda.calculeazaTotal());
                pstmt.executeUpdate();

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        comandaId = generatedKeys.getInt(1);
                    }
                }
            }

            if (comandaId == -1) {
                throw new SQLException("Crearea comenzii a eșuat, nu s-a generat ID.");
            }


            try (PreparedStatement pstmt = conn.prepareStatement(insertProduseSql)) {
                for (Produs p : comanda.getProduse()) {
                    pstmt.setInt(1, comandaId);
                    pstmt.setInt(2, p.getId());
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Comanda> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Comanda> findAll() {
        return new ArrayList<>();
    }

    @Override
    public void update(Comanda comanda) {
        String sql = "UPDATE Comenzi SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, comanda.getStatus());
            pstmt.setInt(2, comanda.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Comenzi WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
