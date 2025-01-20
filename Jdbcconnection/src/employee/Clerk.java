package employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Clerk {
    String userName = "root";
    String pass = "Tushar#123";
    String path = "jdbc:mysql://localhost:3306/employee";
    String query="select c.cust_id,c.cust_name,c.gender,la.app_id,la.loan_status,la.loan_amount,l.loan_type, d.image_data from customers as c left join loan_application as la on c.app_id=la.app_id left join loan as l on la.loan_id=l.loan_id left join documents as d on c.cust_id=d.cust_id where d.image_data IS NOT NULL;\r\n"
    		+ "";
    void addImg() {

        // Path to the image file
        String imagePath = "path/to/your/image.jpg";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        FileInputStream fis = null;

        try {
            // Establish connection
            conn = DriverManager.getConnection(path, userName, pass);
            String userHome = System.getenv("USERPROFILE");
            System.out.println("Enter image name(with type):");
            Scanner sc = new Scanner(System.in);
            String choice = sc.next();
            System.out.println("Enter Customer ID:");
            int choice1 = sc.nextInt();
            sc.close();
            // Construct the path to the Documents folder
            String documentsFolderPath = userHome + "\\Documents";
            // SQL query to insert image
            imagePath=documentsFolderPath+"\\"+choice;
            String sql = "UPDATE documents set image_data=(?) where cust_id=(?)";

            // Create PreparedStatement
            pstmt = conn.prepareStatement(sql);

            // Set the image name and image file
            File file = new File(imagePath);
            fis = new FileInputStream(file);
            
            pstmt.setBinaryStream(1, fis, (int) file.length());
            pstmt.setInt(2, choice1);

            // Execute the query
            pstmt.executeUpdate();

            System.out.println("Image uploaded successfully!");
        }
            catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
                e.printStackTrace();
            }
            finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                if (fis != null) fis.close();
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
                e.printStackTrace();
            }

        }
    }
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

                System.out.println("Name: " + name);
                System.out.println("ID: " + id);
                System.out.println("Gender: " + gen);
                System.out.println("Loan Status: " + loanStatus);
                System.out.println("Loan Type: " + loanType);
                System.out.println("-------------------");
            }
            
        } catch (SQLException e) {
            System.out.println("Connection failed! Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    void addUser() {
        String insertCustomerQuery = "INSERT INTO customers (cust_name, gender, contact) VALUES (?, ?, ?)";
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter customer name:");
        String name = scanner.next();
        System.out.println("Enter Gender:");
        String gender = scanner.next();
        System.out.println("Enter contact number:");
        String contactNo = scanner.next();
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
            insertCustomerStmt.setString(1, name);
            insertCustomerStmt.setString(2, gender);
            insertCustomerStmt.setString(3, contactNo);
            // Execute the update
            int rowsInserted = insertCustomerStmt.executeUpdate();
            if (rowsInserted > 0) {
                // Retrieve the generated keys
                try (ResultSet generatedKeys = insertCustomerStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        System.out.println("Inserted customer ID: " + generatedId);
                        String q2="Insert into documents (cust_id) values (?)";
                        try(PreparedStatement insertNewRow=con.prepareStatement(q2)){
                        	insertNewRow.setInt(1, generatedId);
                        	//System.out.println("sd");
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
