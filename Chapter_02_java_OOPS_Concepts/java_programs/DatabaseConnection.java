public class DatabaseConnection {
    private static DatabaseConnection instance;
    private String connectionString;
    
    private DatabaseConnection() {
        connectionString = "jdbc:mysql://localhost:3306/mydb";
        System.out.println("Database connection created");
    }
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public void connect() {
        System.out.println("Connected to: " + connectionString);
    }
    
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        
        db1.connect();
        System.out.println("Same instance? " + (db1 == db2));  // true
    }
}
