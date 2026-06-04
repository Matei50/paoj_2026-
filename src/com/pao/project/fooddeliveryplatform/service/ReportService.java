package com.pao.project.fooddeliveryplatform.service;

import com.pao.project.fooddeliveryplatform.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportService {
    private static ReportService instance;

    private ReportService() {}

    public static ReportService getInstance() {
        if (instance == null) {
            instance = new ReportService();
        }
        return instance;
    }

    public void afiseazaDetaliiComenzi() {
        String sql = "SELECT c.id AS comanda_id, cl.nume AS nume_client, p.nume AS nume_produs, cp.cantitate " +
                     "FROM Comenzi c " +
                     "JOIN Clienti cl ON c.id_client = cl.id " +
                     "JOIN Comenzi_Produse cp ON c.id = cp.id_comanda " +
                     "JOIN Produse p ON cp.id_produs = p.id";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("--- Detalii Comenzi (JOIN 4 tabele) ---");
            while (rs.next()) {
                System.out.println("Comanda #" + rs.getInt("comanda_id") + 
                                   " | Client: " + rs.getString("nume_client") + 
                                   " | Produs: " + rs.getString("nume_produs") + 
                                   " | Cantitate: " + rs.getInt("cantitate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afiseazaTotalComenziPerClient() {
        String sql = "SELECT cl.nume, COUNT(c.id) AS numar_comenzi " +
                     "FROM Clienti cl " +
                     "LEFT JOIN Comenzi c ON cl.id = c.id_client " +
                     "GROUP BY cl.id, cl.nume";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("--- Numar Comenzi Per Client ---");
            while (rs.next()) {
                System.out.println("Client: " + rs.getString("nume") + " | Comenzi: " + rs.getInt("numar_comenzi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public void afiseazaProduseRestaurant(String numeRestaurant) {
        String sql = "SELECT p.nume AS produs, p.pret, r.nume AS restaurant " +
                     "FROM Produse p " +
                     "JOIN Restaurante r ON p.id_restaurant = r.id " +
                     "WHERE r.nume = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, numeRestaurant);
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("--- Produse pentru restaurantul: " + numeRestaurant + " ---");
                while (rs.next()) {
                    System.out.println("Produs: " + rs.getString("produs") + " | Preț: " + rs.getDouble("pret"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
