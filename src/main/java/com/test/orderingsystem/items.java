/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.orderingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author qweqwe
 */
public class items extends Database{
    
    public static ArrayList<String> burgers = new ArrayList<>();
    public static ArrayList<String> footlongs = new ArrayList<>();
    public static ArrayList<String> drinks = new ArrayList<>();
    
    
    items(){
        startItems();
    }
    
    public static void storeBurgers(){
        Connection conn = Database.conn();
        PreparedStatement pst;
        ResultSet rs;
        burgers.add("");
        
        try {
            int i = 1;
            String sqlQuery = "SELECT * FROM items WHERE iCategory = ?";
            pst = conn.prepareStatement(sqlQuery);
            pst.setInt(1, 1);
            rs = pst.executeQuery();
            while(rs.next()){
                String name = rs.getString("iName");
                float price = rs.getFloat("iPrice");
                String cap = capFirstLetter(name);
                burgers.add(cap);
                System.out.println("["+i+"] P"+price+"      |"+cap);
                i++;
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public static void storeFootlongs(){
        Connection conn = Database.conn();
        PreparedStatement pst;
        ResultSet rs;
        footlongs.add("");
        
        try {
            int i = 1;
            String sqlQuery = "SELECT * FROM items WHERE iCategory = ?";
            pst = conn.prepareStatement(sqlQuery);
            pst.setInt(1, 2);
            rs = pst.executeQuery();
            while(rs.next()){
                String name = rs.getString("iName");
                float price = rs.getFloat("iPrice");
                String cap = capFirstLetter(name);
                
                footlongs.add(cap);
                System.out.println("["+i+"] P"+price+"      |"+cap);
                i++;
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public static void storeDrinks(){
        Connection conn = Database.conn();
        PreparedStatement pst;
        ResultSet rs;
        drinks.add("");
        
        try {
            int i = 1;
            String sqlQuery = "SELECT * FROM items WHERE iCategory = ?";
            pst = conn.prepareStatement(sqlQuery);
            pst.setInt(1, 3);
            rs = pst.executeQuery();
            while(rs.next()){
                String name = rs.getString("iName");
                float price = rs.getFloat("iPrice");
                String cap = capFirstLetter(name);
                
                drinks.add(cap);
                System.out.println("["+i+"] P"+price+"      |"+cap);
                i++;
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public static void startItems(){
        storeBurgers();
        System.out.println("");
        storeFootlongs();
        System.out.println("");
        storeDrinks();
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
