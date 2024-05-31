/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.orderingsystem;
import static com.test.orderingsystem.items.burgers;
import static com.test.orderingsystem.Category.cat;
import static com.test.orderingsystem.items.capFirstLetter;
import static com.test.orderingsystem.items.footlongs;
import static com.test.orderingsystem.items.drinks;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author qweqwe
 */
public class Main extends Database{
    static String url = "jdbc:mysql://localhost:3306/resburg";
    static String user = "admin";
    static String pass = "root";
    static double sumB = 0;
    static int lastInsertedId = 0;
    
    ArrayList<String> orders = new ArrayList<>();
    ArrayList<Double> ordersPrice = new ArrayList<>();
    ArrayList<Integer> orderQuantity = new ArrayList<>();
    
    public void displayCategory(){
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
                System.out.println("["+i+"] "+cap);
                i++;
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void burgerSelection(){
        System.out.println("Burgers:");
        System.out.println("[0] Back");
        
        Connection conn = Database.conn();
        PreparedStatement pst;
        ResultSet rs;
        
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
                System.out.println("["+i+"] P"+price+"      |"+cap);
                i++;
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void footlongSelection(){
        System.out.println("Footlongs:");
        System.out.println("[0] Back");
        
        Connection conn = Database.conn();
        PreparedStatement pst;
        ResultSet rs;
        
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
                System.out.println("["+i+"] P"+price+"      |"+cap);
                i++;
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    public void drinkSelection(){
        System.out.println("Drinks:");
        System.out.println("[0] Back");
        
        Connection conn = Database.conn();
        PreparedStatement pst;
        ResultSet rs;
        
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
                System.out.println("["+i+"] P"+price+"      |"+cap);
                i++;
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void Order(){
        Scanner scan = new Scanner(System.in);
        
        int itemId = 0;
        String orderMore ="";
        
        boolean case1Loop;
        String usrOrderCStr = "";
        int usrOrderC = 0;
        
        do {
            System.out.println("-------------------------------------------");
            System.out.println("Menu category:");
            System.out.println("[0] Exit");
            displayCategory();
            System.out.println("");
            System.out.println("[5] Complete Order");
            System.out.print("Select category: ");
            String usrOrder = scan.nextLine();
            
                if(isNumeric(usrOrder)){

                    int usrOrderB = Integer.parseInt(usrOrder);
                    if(usrOrderB == 0){
                        System.out.println("System is terminated.");
                        System.exit(0);
                    }
                   
                    
                        if((usrOrderB > 0 && usrOrderB <= Category.cat.size()) || usrOrderB == 5){ // 5 > 0 t && 5 < 3 f

                            if(usrOrderB == 1){
                                case1Loop = true;
                                while(case1Loop){
                                        System.out.println("-------------------------------------------");
                                        burgerSelection();
                                        System.out.print("Select burger: ");
                                        usrOrderCStr = scan.nextLine();
                                        
                                        if(isNumeric(usrOrderCStr)){
                                            usrOrderC = Integer.parseInt(usrOrderCStr);
                                            
                                                if(usrOrderC == 0){
                                                    Order();
                                                }

                                                if(usrOrderC > 0 && usrOrderC < items.burgers.size()){
                                                    Double convertPrice = Double.parseDouble(Price.burgersPrice.get(usrOrderC));

                                                    System.out.print("Quantity: ");
                                                    int qty = Integer.parseInt(scan.nextLine());
                                                    double sumA = qty * convertPrice;

                                                    String selectionB = "";
                                                    System.out.print("Are you sure about your order?(Y/N) ");//y
                                                    selectionB = scan.nextLine();
                                                    do{
                                                        if(selectionB.equalsIgnoreCase("yes") || selectionB.equalsIgnoreCase("y")){
                                                            case1Loop = false;
                                                            orders.add(items.burgers.get(usrOrderC));
                                                            ordersPrice.add(sumA);
                                                            orderQuantity.add(qty);

                                                           try {
                                                               String sqlQuery = "SELECT iID FROM items WHERE iName = ?";
                                                               String itemName = items.burgers.get(usrOrderC); 

                                                               Connection connection = null;
                                                               PreparedStatement statement = null;
                                                               ResultSet resultSet = null;

                                                               connection = DriverManager.getConnection(url, user, pass);
                                                               statement = connection.prepareStatement(sqlQuery);

                                                               // Set the search parameter (item name)
                                                               statement.setString(1, itemName);

                                                               // Execute the query and get the results
                                                               resultSet = statement.executeQuery();

                                                               if (resultSet.next()) {
                                                                   itemId = resultSet.getInt("iID");

                                                               } else {
                                                                   System.out.println("Item '" + itemName + "' not found in the database.");
                                                               }

                                                           } catch (SQLException e) {
                                                               e.printStackTrace();
                                                           } 


                                                            try{
                                                                String query = "INSERT INTO customer_preference(cpCustomerID, cpCustomerItemOrder, cpPrice, cpQuantity) VALUES (?,?,?,?)" ;
                                                                Connection conn = DriverManager.getConnection(url, user, pass);
                                                                PreparedStatement pst = conn.prepareStatement(query);
                                                                pst.setInt(1, lastInsertedId);
                                                                pst.setInt(2, itemId);
                                                                pst.setDouble(3, sumA);
                                                                pst.setInt(4, qty);

                                                                int rowsAffected = pst.executeUpdate();
                                                                if(rowsAffected > 0){
                                                                    System.out.println("Inserted preference");

                                                                    String queryB = "INSERT INTO item_purchased_counter"+
                                                                            "(ipcItem, ipcQuantity, ipcDate) VALUES(?,?,?)";
                                                                    pst = conn.prepareStatement(queryB);
                                                                    pst.setInt(1, itemId);
                                                                    pst.setInt(2, qty);
                                                                    pst.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                                                                    rowsAffected = pst.executeUpdate();
                                                                    if(rowsAffected > 0){

                                                                    } else {
                                                                        System.out.println("Failed");
                                                                    }

                                                                } else {
                                                                    System.out.println("Failed");
                                                                }

                                                            }catch(SQLException ex) {
                                                                ex.printStackTrace();
                                                            }

                                                            break;
                                                        } else if(selectionB.equalsIgnoreCase("no") || selectionB.equalsIgnoreCase("n")){
                                                            selectionB = "no";
                                                        } else {
                                                            continue;
                                                        }
                                                    }while(!selectionB.equalsIgnoreCase("no"));

                                                    }else{
                                                        System.out.println("Invalid input.");
                                                    }

                                        } else {
                                            System.out.println("Invalid input.");
                                        }
                                        
                                        System.out.println("-------------------------------------------");
                                        
                                    }
                                
                            } else if(usrOrderB == 2){
                                case1Loop = true;
                                while(case1Loop){
                                        System.out.println("-------------------------------------------");
                                        footlongSelection();
                                        System.out.print("Select footlong: ");
                                        usrOrderCStr = scan.nextLine();
                                        
                                        if(isNumeric(usrOrderCStr)){
                                            usrOrderC = Integer.parseInt(usrOrderCStr);
                                            
                                            
                                            if(usrOrderC == 0){
                                            Order();
                                        }
                                        
                                        if(usrOrderC > 0 && usrOrderC < items.footlongs.size()){
                                            Double convertPrice = Double.parseDouble(Price.footlongsPrice.get(usrOrderC));

                                            System.out.print("Quantity: ");
                                            int qty = Integer.parseInt(scan.nextLine());
                                            double sumA = qty * convertPrice;

                                            String selectionB = "";
                                            System.out.print("Are you sure about your order?(Y/N) ");//y
                                            selectionB = scan.nextLine();
                                            do{
                                                if(selectionB.equalsIgnoreCase("yes") || selectionB.equalsIgnoreCase("y")){
                                                    case1Loop = false;
                                                    orders.add(items.footlongs.get(usrOrderC));
                                                    ordersPrice.add(sumA);
                                                    orderQuantity.add(qty);
                                                    
                                                   try {
                                                       String sqlQuery = "SELECT iID FROM items WHERE iName = ?";
                                                       String itemName = items.footlongs.get(usrOrderC); // Replace with the item name you want to search for

                                                       Connection connection = null;
                                                       PreparedStatement statement = null;
                                                       ResultSet resultSet = null;

                                                       // Establish connection to the database
                                                       connection = DriverManager.getConnection(url, user, pass);

                                                       // Prepare a SQL query to select item ID based on name
                                                       statement = connection.prepareStatement(sqlQuery);

                                                       // Set the search parameter (item name)
                                                       statement.setString(1, itemName);

                                                       // Execute the query and get the results
                                                       resultSet = statement.executeQuery();

                                                       if (resultSet.next()) {
                                                           itemId = resultSet.getInt("iID");
                                                           
                                                       } else {
                                                           System.out.println("Item '" + itemName + "' not found in the database.");
                                                       }

                                                   } catch (SQLException e) {
                                                       e.printStackTrace();
                                                   } 
                                                    
                                                    try{
                                                        String query = "INSERT INTO customer_preference(cpCustomerID, cpCustomerItemOrder, cpPrice, cpQuantity) VALUES (?,?,?,?)" ;
                                                        Connection conn = DriverManager.getConnection(url, user, pass);
                                                        PreparedStatement pst = conn.prepareStatement(query);
                                                        pst.setInt(1, lastInsertedId);
                                                        pst.setInt(2, itemId);
                                                        pst.setDouble(3, sumA);
                                                        pst.setInt(4, qty);

                                                        int rowsAffected = pst.executeUpdate();
                                                        if(rowsAffected > 0){
                                                           
                                                        } else {
                                                            System.out.println("Failed");
                                                        }
                                                        
                                                    }catch(SQLException ex) {
                                                        ex.printStackTrace();
                                                    }
                                                    
                                                    break;
                                                } else if(selectionB.equalsIgnoreCase("no") || selectionB.equalsIgnoreCase("n")){
                                                    selectionB = "no";
                                                } else {
                                                    
                                                }
                                            }while(!selectionB.equalsIgnoreCase("no"));
                                            
                                        }else{
                                            System.out.println("Invalid input.");
                                        }
                                            
                                        } else {
                                            System.out.println("Invalid input.");
                                        }
                                        
                                        System.out.println("-------------------------------------------");
                                    }
                            } else if(usrOrderB == 3){
                                case1Loop = true;
                                while(case1Loop){
                                    System.out.println("-------------------------------------------");
                                    drinkSelection();
                                        System.out.print("Select drink: ");
                                        usrOrderCStr = scan.nextLine();
                                        
                                        if(isNumeric(usrOrderCStr)){
                                            usrOrderC = Integer.parseInt(usrOrderCStr);
                                            
                                            if(usrOrderC == 0){
                                            Order();
                                        }
                                        
                                        if(usrOrderC > 0 && usrOrderC < items.drinks.size()){
                                            Double convertPrice = Double.parseDouble(Price.drinksPrice.get(usrOrderC));

                                            System.out.print("Quantity: ");
                                            int qty = Integer.parseInt(scan.nextLine());
                                            double sumA = qty * convertPrice;
                                            
                                            String selectionB = "";
                                            System.out.print("Are you sure about your order?(Y/N) ");//y
                                            selectionB = scan.nextLine();
                                            do{
                                                if(selectionB.equalsIgnoreCase("yes") || selectionB.equalsIgnoreCase("y")){
                                                    case1Loop = false;
                                                    orders.add(items.drinks.get(usrOrderC));
                                                    ordersPrice.add(sumA);
                                                    orderQuantity.add(qty);
                                                    
                                                    try {
                                                       String sqlQuery = "SELECT iID FROM items WHERE iName = ?";
                                                       String itemName = items.drinks.get(usrOrderC); // Replace with the item name you want to search for

                                                       Connection connection = null;
                                                       PreparedStatement statement = null;
                                                       ResultSet resultSet = null;

                                                       // Establish connection to the database
                                                       connection = DriverManager.getConnection(url, user, pass);

                                                       // Prepare a SQL query to select item ID based on name
                                                       statement = connection.prepareStatement(sqlQuery);

                                                       // Set the search parameter (item name)
                                                       statement.setString(1, itemName);

                                                       // Execute the query and get the results
                                                       resultSet = statement.executeQuery();

                                                       if (resultSet.next()) {
                                                           itemId = resultSet.getInt("iID");
                                                           
                                                       } else {
                                                           System.out.println("Item '" + itemName + "' not found in the database.");
                                                       }

                                                   } catch (SQLException e) {
                                                       e.printStackTrace();
                                                   } 

                                                    try{
                                                        String query = "INSERT INTO customer_preference(cpCustomerID, cpCustomerItemOrder, cpPrice, cpQuantity) VALUES (?,?,?,?)" ;
                                                        Connection conn = DriverManager.getConnection(url, user, pass);
                                                        PreparedStatement pst = conn.prepareStatement(query);
                                                        pst.setInt(1, lastInsertedId);
                                                        pst.setInt(2, itemId);
                                                        pst.setDouble(3, sumA);
                                                        pst.setInt(4, qty);

                                                        int rowsAffected = pst.executeUpdate();
                                                        if(rowsAffected > 0){
                                                            
                                                        } else {
                                                            System.out.println("Failed");
                                                        }
                                                        
                                                    }catch(SQLException ex) {
                                                        ex.printStackTrace();
                                                    }
                                                    
                                                    break;
                                                } else if(selectionB.equalsIgnoreCase("no") || selectionB.equalsIgnoreCase("n")){
                                                    selectionB = "no";
                                                } else {
                                                }
                                            }while(!selectionB.equalsIgnoreCase("no"));

                                        }else{
                                            System.out.println("Invalid input.");
                                        }
                                            
                                        } else {
                                            System.out.println("Invalid input.");
                                        }
                                        
                                        System.out.println("-------------------------------------------");
                                    }
                            }  else if(usrOrderB == 5){
                                    if(orders.size() == 0) { // 0 == 0 = true
                                        System.out.println("Can't use 5, yet.");
                                        continue;
                                    } else {
                                        break;
                                    }
                                } else {
                                System.out.println("Invalid");
                            }

                        } else {
                            System.out.println("Invalid input, please try again.");
                            Order();
                        }
                }else {
                    System.out.println("Invalid input, please try again.");
                    Order();
                }
            
            

            System.out.println("Orders: ");
            for(int i = 0; i < orders.size(); i++){ // i = 0; 0 <= 1 -> T
                System.out.println("    [P"+ordersPrice.get(i)+"] "+orders.get(i)+" x("+orderQuantity.get(i)+")");

            }

            System.out.print("Do you want to order more? (Y/N): ");
            orderMore = scan.nextLine().trim(); 

            if (orderMore.equalsIgnoreCase("yes") || orderMore.equalsIgnoreCase("y")) {
                
            }
            else if(orderMore.equalsIgnoreCase("no") || orderMore.equalsIgnoreCase("n")){
                orderMore = "no";
            }
            System.out.println("-------------------------------------------");
        } while (!orderMore.equalsIgnoreCase("no"));
        
        for(double number : ordersPrice){
            sumB+=number;
        }

        try{
           String query = "INSERT INTO customer_payment (cpayCustomerID, cpayTotalPrice) "
                   + "VALUES (?,?)"; 
           Connection conn = DriverManager.getConnection(url, user, pass);
           PreparedStatement pst = conn.prepareStatement(query);
           pst.setInt(1, lastInsertedId);
           pst.setDouble(2, sumB);

           int rowsAffected = pst.executeUpdate();
           if(rowsAffected > 0){
               System.out.println("Your order is now pending.");
           } else {
               System.out.println("Failed");
           }

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        System.out.println("");
        System.out.println("-------------------------------------------");
        System.out.println("Orders: ");
        for(int i = 0; i < orders.size(); i++){
            System.out.println("    [P"+ordersPrice.get(i)+"] "+orders.get(i)+" x("+orderQuantity.get(i)+")");

        }
        System.out.println("");
        System.out.println("Total payment: " + sumB);
        
        System.out.println("");
        System.out.println("Thank you for order.");
        System.out.println("-------------------------------------------");
        System.out.println("");
        
        scan.close();
        System.exit(0);
    }
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        Main m = new Main();
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
        new Category();
        new items();
        new Price();
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
        Customer customer = new Customer();
        
        String name= "";
        String contact = "";
        String selection = "";
        
        System.out.println("What do we call you?");
        System.out.print("Enter your name: ");
        name = scan.nextLine();
        customer.setName(name);
        do {
            System.out.println("Hi, "+ customer.getName()+".");
            System.out.print("Do you want to enter your contact, so that we can call you as soon your order is ready? (Y/N) ");
            selection = scan.nextLine();
            if(selection.equalsIgnoreCase("yes") || selection.equalsIgnoreCase("y")){
                System.out.print("Enter your contact: ");
                contact = scan.nextLine();
                break;
            } else if(selection.equalsIgnoreCase("no") || selection.equalsIgnoreCase("n")){
                selection = "no";
            } else {
                System.out.println("Invalid input, try again.");
                continue;
            }
        }while(!selection.equalsIgnoreCase("no"));
        
        
        try {
            String sqlQuery = "INSERT INTO customer" +
                    "(cusName, cusContact)" + "VALUES" + 
                    "(?, ?)";
            Connection conn = DriverManager.getConnection(url, user, pass);
            PreparedStatement pst = conn.prepareStatement(sqlQuery);
            pst.setString(1, name);
            pst.setString(2, contact);
            
            int rowsAffected = pst.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Inserted");
                
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");

                if (rs.next()) {
                  lastInsertedId = rs.getInt(1);
                  System.out.println("Customer inserted with ID: " + lastInsertedId);
                } else {
                  System.out.println("Failed to retrieve ID");
                }

                rs.close(); // Close the ResultSet
                stmt.close(); // Close the Statement
            } else {
                System.out.println("Failed");
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        
        System.out.println("");
        System.out.println("");
        m.Order();
    }
    
    public static boolean isNumeric(String str) {
        
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
}
