import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Connection con = null;
        try {
            // Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection to the database
            String url = "jdbc:mysql://localhost:3306/class_learning";
            String username = "root";
            String password = "sspear54321";

            con = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected");

            while (true) {
                System.out.println("Customer Management System");
                System.out.println("+++++++++++++++++++++++++++++");
                System.out.println("1. Add the customer");
                System.out.println("2. Delete the customer");
                System.out.println("3. Update the customer");
                System.out.println("4. View customers");
                System.out.println("5. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                Statement statement = con.createStatement();

                switch (choice) {
                    case 1:
                        System.out.println("Enter customer ID:");
                        String customerId = scanner.nextLine();
                        System.out.println("Enter First Name:");
                        String firstName = scanner.nextLine();
                        System.out.println("Enter Last Name:");\
                        String lastName = scanner.nextLine();
                        System.out.println("Enter Email:");
                        String email = scanner.nextLine();
                        System.out.println("Enter phone number:");
                        String phoneNumber = scanner.nextLine();
                     /* 
                        String query1 = "INSERT INTO customers (CustomerID, FirstName, LastName, Email, Phone) VALUES('" + customerId + "', '" + firstName + "', '" + lastName + "', '" + email + "', '" + phoneNumber + "')";
                        System.out.println("Executing query: " + query1);
                        statement.executeUpdate(query1);

                        System.out.println("Customer added");
                                         */
                              

                      PreparedStatement ps = con.prepareStatement("insert into Customers (FirstName, LastName, Email, Phone) values(?,?,?,?) ");
                      ps.setString(1, firstName);
                      ps.setString(2, lastName);
                      ps.setString(3, email);
                      ps.setString(4, phoneNumber);

                      ps.executeUpdate();

                        break;

                    case 2:
                         System.out.println("Enter ID to delete:");
                        String idDelete = scanner.nextLine();

                        String query2 = "DELETE FROM customers WHERE CustomerID = '" + idDelete + "'";
                        System.out.println("Executing query: " + query2);
                        statement.executeUpdate(query2);

                        System.out.println("Customer deleted");
                        break;
                       

                 
                    case 3:
                        System.out.println("Enter customer ID to update:");
                        String updCustomerId = scanner.nextLine();

                        System.out.println("Enter new First Name:");
                        String newFirstName = scanner.nextLine();
                        System.out.println("Enter new Last Name:");
                        String newLastName = scanner.nextLine();
                        System.out.println("Enter new Email:");
                        String newEmail = scanner.nextLine();
                        System.out.println("Enter new phone number:");
                        String newPhoneNumber = scanner.nextLine();

                        String query3 = "UPDATE customers SET FirstName = '" + newFirstName + "', LastName = '" + newLastName + "', Email = '" + newEmail + "', Phone = '" + newPhoneNumber + "' WHERE CustomerID = '" + updCustomerId + "'";
                        System.out.println("Executing query: " + query3);
                        int rowsAffected = statement.executeUpdate(query3);

                        if (rowsAffected > 0) {
                            System.out.println("Customer updated");
                        } else {
                            System.out.println("Customer with ID " + updCustomerId + " not found.");
                        }
                        break;

                    case 4:
                        String query4 = "SELECT * FROM customers";
                        System.out.println("Executing query: " + query4);
                        ResultSet rs = statement.executeQuery(query4);

                        while (rs.next()) {
                            System.out.println(rs.getString("CustomerID") + " " + rs.getString("FirstName") + " " + rs.getString("LastName") + " " + rs.getString("Email") + " " + rs.getString("Phone"));
                        }

                        System.out.println("Customers viewed");
                        break;

                    case 5:
                        scanner.close();
                        con.close();
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
