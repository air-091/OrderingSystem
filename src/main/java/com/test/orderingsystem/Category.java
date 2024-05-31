/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.orderingsystem;

import static com.test.orderingsystem.items.burgers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author qweqwe
 */
public class Category extends Database{
    
    public static ArrayList<String> cat = new ArrayList<>();
    
    
    
    Category(){
        startCategory();
    }
    
    public static void startCategory(){
        int i = 1;
        Connection conn = Database.conn();
        PreparedStatement pst;
        ResultSet rs;
        
        try {
            
            String sqlQuery = "SELECT * FROM category";
            pst = conn.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
            while(rs.next()){
                String name = rs.getString("cName");
                String cap = capFirstLetter(name);
                
                cat.add(cap);
                System.out.println("["+i+"] "+cap);
                i++;
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public static String capFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str; // Return the input string if it's null or empty
        } else {
            // Split the input string into words
            String[] words = str.split("\\s+");

            // Capitalize the first letter of each word
            StringBuilder result = new StringBuilder();
            for (String word : words) {
                if (!word.isEmpty()) {
                    result.append(word.substring(0, 1).toUpperCase()); // Uppercase the first letter
                    result.append(word.substring(1).toLowerCase()); // Lowercase the rest of the word
                    result.append(" "); // Add a space after each word
                }
            }

            // Remove the trailing space and return the result
            return result.toString().trim();
        }
    }
    
}
