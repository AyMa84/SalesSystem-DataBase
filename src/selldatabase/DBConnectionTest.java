/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selldatabase;

/**
 *
 * @author ASUS
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnectionTest {
    private static final String URL = "jdbc:mysql://localhost:3306/account"; // 'account' is your schema
    private static final String USER = "root";
    private static final String PASSWORD = "ay84lin13"; // Add your MySQL password if any

    public static void testConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database connected successfully!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to the database.");
            e.printStackTrace();
        }
    }
}

