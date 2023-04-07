package accountdatabase;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseControl {
    private Connection conn;

    public DatabaseControl() throws IOException {
    	
    	Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream("lab7out/db.properties");
		properties.load(fileInputStream);
		
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String pass = properties.getProperty("password");
		
        try {
            this.conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = this.conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS accounts (username VARCHAR(255) PRIMARY KEY, password VARCHAR(255))");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void storeAccount(String username, String password) {
        String hashedPassword = hashPassword(password);
        String sql = "INSERT INTO accounts (username, password) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPassword);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyAccount(String username, String password) {
        String hashedPassword = hashPassword(password);
        String sql = "SELECT password FROM accounts WHERE username = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                pstmt.close();
                return hashedPassword.equals(storedPassword);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String hashPassword(String password) {
        // You can use any hash function here, e.g. SHA-256
        return "hashed_" + password;
    }

    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}