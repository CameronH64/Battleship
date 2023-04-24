package accountdatabase;

import java.io.IOException;
import java.util.ArrayList;

public class TestAccountDatabase {

    private AccountDatabase db;

    public TestAccountDatabase() throws IOException {
        this.db = new AccountDatabase();
    }

    public void close() {
        db.close();
    }

    public boolean verifyAccount(String username, String password) {
        return db.verifyAccount(username, password);
    }

    public void storeAccount(String username, String password) {
        db.storeAccount(username, password);
    }

    public void deleteAccount(String username) {
        String sql = "DELETE FROM accounts WHERE username = (?)";
        try {
            db.executeDML(sql);
            System.out.println("Account deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> query(String query) {
        return db.query(query);
    }

    public void executeDML(String dml) {
        try {
            db.executeDML(dml);
            System.out.println("DML executed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TestAccountDatabase testDb = new TestAccountDatabase();

        // Store an account
        testDb.storeAccount("user1", "password1");

        // Verify the account
        boolean verified = testDb.verifyAccount("user1", "password1");
        System.out.println("Account verified: " + verified);

        // Delete the account
        testDb.deleteAccount("user1");

        // Execute a DML statement
        testDb.executeDML("INSERT INTO accounts (username, password) VALUES ('user2', 'password2')");

        // Query the database
        ArrayList<String> results = testDb.query("SELECT * FROM accounts");
        System.out.println("Query results:");
        for (String result : results) {
            System.out.println(result);
        }

        // Close the database connection
        testDb.close();
    }
}