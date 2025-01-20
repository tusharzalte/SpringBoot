package employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Manager {
	String userName = "root";
    String pass = "Tushar#123";
    String query="select c.cust_id,c.cust_name,c.gender,la.app_id,la.loan_status,la.loan_amount,l.loan_type, d.image_data from customers as c left join loan_application as la on c.app_id=la.app_id left join loan as l on la.loan_id=l.loan_id left join documents as d on c.cust_id=d.cust_id where d.image_data IS NOT NULL;\r\n"
    		+ "";
    String path = "jdbc:mysql://localhost:3306/employee";
    void show() {
        // Load the MySQL JDBC driver (optional, but can be useful in some environments)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Include it in your library path.");
            e.printStackTrace();
            return;
        }

        try (Connection con = DriverManager.getConnection(path, userName, pass);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Establishing the connection
            System.out.println("Connection successful!");

            while (rs.next()) {
                int id = rs.getInt("cust_id");
                String name = rs.getString("cust_name");
                String gen = rs.getString("gender");
                String loanStatus = rs.getString("loan_status");
                String loanType = rs.getString("loan_type");
                int lamt=rs.getInt("loan_amount");

                System.out.println("Name: " + name);
                System.out.println("ID: " + id);
                System.out.println("Gender: " + gen);
                System.out.println("Loan Status: " + loanStatus);
                System.out.println("Loan Type: " + loanType);
                System.out.println("Loan Amount: "+lamt);
                System.out.println("-------------------");
            }
            
        } catch (SQLException e) {
            System.out.println("Connection failed! Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    void setStatus() {
    	 System.out.println("Enter the application id to approve:");
         Scanner scanner = new Scanner(System.in);
         int appId = scanner.nextInt();
         scanner.close(); // Close the scanner

         String query = "UPDATE loan_application SET loan_status = 'Approved' WHERE app_id = ?";
//         String query1="UPADTE customers SET ";

         try {
             // Load the JDBC driver
             Class.forName("com.mysql.cj.jdbc.Driver");
         } catch (ClassNotFoundException e) {
             System.out.println("MySQL JDBC Driver not found. Include it in your library path.");
             e.printStackTrace();
             return;
         }

         try (Connection con = DriverManager.getConnection(path, userName, pass);
              PreparedStatement pstmt = con.prepareStatement(query)) {

             // Set the parameter for the query
             pstmt.setInt(1, appId);

             // Execute the update
             int rowsAffected = pstmt.executeUpdate();
             if (rowsAffected > 0) {
                 System.out.println("Loan application approved successfully!");
             } else {
                 System.out.println("No loan application found with the provided ID.");
             }

         } catch (SQLException e) {
             System.out.println("Database error: " + e.getMessage());
             e.printStackTrace();
         }
    }
}
	


