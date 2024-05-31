
package com.test.orderingsystem;

import java.util.Scanner;
import java.sql.*;
import javax.swing.JOptionPane;

public class Database {
    
    public static void main(String[] args){
        
        
//         try {
//             // Replace these with your actual database connection details
//            String url = "jdbc:mysql://localhost:3306/resburg";
//            String username = "admin";
//            String password = "root";
//            
//            String itemName = "plain footlong hotdog"; // Replace with the item name you want to search for
//
//            Connection connection = null;
//            PreparedStatement statement = null;
//            ResultSet resultSet = null;
//            // Load the JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Establish connection to the database
//            connection = DriverManager.getConnection(url, username, password);
//
//            // Prepare a SQL query to select item ID based on name
//            String sqlQuery = "SELECT iID FROM items WHERE iName = ?";
//            statement = connection.prepareStatement(sqlQuery);
//
//            // Set the search parameter (item name)
//            statement.setString(1, itemName);
//
//            // Execute the query and get the results
//            resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                int itemId = resultSet.getInt("iID");
//                System.out.println("ID of '" + itemName + "': " + itemId);
//            } else {
//                System.out.println("Item '" + itemName + "' not found in the database.");
//            }
//
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        } 
    }
    
    public static Connection conn(){
        
        try{
            String url = "jdbc:mysql://localhost:3306/resburg";
            String username = "admin";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
            
        }catch(SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        return null;
    }
    
    public static void startDatabase(){
        Connection conn = Database.conn();
        PreparedStatement pst;
        ResultSet rs;
        
        try {
            
            String sqlQuery = "SELECT * FROM items WHERE iCategory = ?";
            pst = conn.prepareStatement(sqlQuery);
            pst.setInt(1, 2);
            rs = pst.executeQuery();
            while(rs.next()){
                String name = rs.getString("iName");
                System.out.println(name);
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
