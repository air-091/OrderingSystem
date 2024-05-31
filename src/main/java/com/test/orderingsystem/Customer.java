/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.orderingsystem;
import java.util.Scanner;
public class Customer {
    
    String name, contact;
    
    public Customer(){  
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public void setContact(String contact){
        this.contact = contact;
    }
    public String getContact(){
        return contact;
    }
}
