/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.com.salesmanagement;

import az.com.salesmanagement.dao.CustomersDao;
import az.com.salesmanagement.db.DbConfig;
import az.com.salesmanagement.model.Customers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vafa
 */
public class Salesmanagement {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        CustomersDao dao=new CustomersDao();
//        Customers customer=new Customers(9, "Handalf", "1937194519", 1);
//        dao.addCustomer(customer);
//        dao.updateCustomerName("Fatime", 8);
        for(Customers e: dao.customers()){
            System.out.println(e);
        }
        
    }
}
