import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/paoj_proiect";
        String user = "root";
        String pass = "root";
        
        try {
            System.out.println("Incercare conectare la: " + url);
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexiune reusita!");
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW TABLES");
            System.out.println("Tabele in baza de date:");
            while(rs.next()) {
                System.out.println("- " + rs.getString(1));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
