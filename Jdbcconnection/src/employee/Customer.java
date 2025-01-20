package employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;

class Customer {
	String userName = "root";
    String pass = "Tushar#123";
    String query="Select * from customers";
    String path = "jdbc:mysql://localhost:3306/employee";
	void applyLoan() {
		String insertCustomerQuery = "INSERT INTO loan_application (loan_id,loan_amount) VALUES (?,?)";
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter your customer id:");
        int id = scanner.nextInt();
        System.out.println("enter loan amount");
        int amt=scanner.nextInt();
        System.out.println("Enter 1 for home loan, 2 for education loan, 3 for car loan:");
        int loanchoice = scanner.nextInt();
        if (loanchoice < 1 || loanchoice > 3) {
            System.out.println("Enter a correct loan choice.");
            scanner.close();
            return;
        }
        scanner.close();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
            return;
        }

        try (Connection con = DriverManager.getConnection(path, userName, pass);
             PreparedStatement insertCustomerStmt = con.prepareStatement(insertCustomerQuery, Statement.RETURN_GENERATED_KEYS)) {

            // Set the parameters
            // Assuming cust_id is not auto-incremented
            insertCustomerStmt.setInt(1, loanchoice);
            insertCustomerStmt.setInt(2, amt);

            // Execute the update
            int rowsInserted = insertCustomerStmt.executeUpdate();
            if (rowsInserted > 0) {
                // Retrieve the generated keys
                try (ResultSet generatedKeys = insertCustomerStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        System.out.println("Inserted customer ID: " + generatedId);
                        String q2="Update customers set app_id=(?) where cust_id=(?)";
                        try(PreparedStatement insertNewRow=con.prepareStatement(q2)){
                        	insertNewRow.setInt(1, generatedId);
                        	insertNewRow.setInt(2, id);
                        	System.out.println("sd");
                        	int rowsInserted2= insertNewRow.executeUpdate();
                        }catch(SQLException e){
                        	System.out.println("Database error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("No ID obtained.");
                    }
                }
            } else {
                System.out.println("Insert failed.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
		
	}
}
