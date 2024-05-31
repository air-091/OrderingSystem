/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.orderingsystem;

import static com.test.orderingsystem.items.burgers;
import static com.test.orderingsystem.items.capFirstLetter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Price extends Database{
    
    public static ArrayList<String> burgersPrice = new ArrayList<>();
    public static ArrayList<String> footlongsPrice = new ArrayList<>();
    public static ArrayList<String> drinksPrice = new ArrayList<>();
    
    
    
    Price(){
        startPrices();
    }
    
    public static void startPrices(){
        storeBurgerPrice();
        storeFootlongPrice();
        storeDrinkPrice();
    }
    
    public static void storeBurgerPrice(){
        Connection conn = Database.conn();
        PreparedStatement pst;
        ResultSet rs;
            burgersPrice.add("");
        
        try {
            String sqlQuery = "SELECT * FROM items WHERE iCategory = ?";
            pst = conn.prepareStatement(sqlQuery);
            pst.setInt(1, 1);
            rs = pst.executeQuery();
            while(rs.next()){
                String price = rs.getString("iPrice");
                burgersPrice.add(price);
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public static void storeFootlongPrice(){
        Connection conn = Database.conn();
        PreparedStatement pst;
        ResultSet rs;
        footlongsPrice.add("");
        
        try {
            String sqlQuery = "SELECT * FROM items WHERE iCategory = ?";
            pst = conn.prepareStatement(sqlQuery);
            pst.setInt(1, 2);
            rs = pst.executeQuery();
            while(rs.next()){
                String price = rs.getString("iPrice");
                footlongsPrice.add(price);
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public static void storeDrinkPrice(){
        Connection conn = Database.conn();
        PreparedStatement pst;
        ResultSet rs;
        drinksPrice.add("");
        
        try {
            String sqlQuery = "SELECT * FROM items WHERE iCategory = ?";
            pst = conn.prepareStatement(sqlQuery);
            pst.setInt(1, 3);
            rs = pst.executeQuery();
            while(rs.next()){
                String price = rs.getString("iPrice");
                drinksPrice.add(price);
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
}
