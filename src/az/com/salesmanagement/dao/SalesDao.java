/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.com.salesmanagement.dao;

import az.com.salesmanagement.db.DbConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vafa
 */
public class SalesDao {
    private Connection conn=null;
    public List<String> buyers() throws SQLException{
        List<String> names=new ArrayList<>();
        String query="select name from customers where type=1";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            ResultSet rst=pst.executeQuery();
            while(rst.next()){
                String name=rst.getString("name");
                names.add(name);
            }
        }  catch (SQLException ex) {
            System.err.println("Baza tapilmadi");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver tapilmadi");
        }finally{
            if(conn!=null) conn.close();
        }
        return names;
    }
    public List<String> sellers() throws SQLException{
        List<String> names=new ArrayList<>();
        String query="select name from customers where type=2";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            ResultSet rst=pst.executeQuery();
            while(rst.next()){
                String name=rst.getString("name");
                names.add(name);
            }
        }  catch (SQLException ex) {
            System.err.println("Baza tapilmadi");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver tapilmadi");
        }finally{
            if(conn!=null) conn.close();
        }
        return names;
    }
    public List<String> products() throws SQLException{
        List<String> names=new ArrayList<>();
        String query="select name, stock_count from products";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            ResultSet rst=pst.executeQuery();
            while(rst.next()){
                String name=rst.getString("name");
                Integer stockCount=rst.getInt("stock_count");
                if(stockCount!=0) names.add(name);
            }
        }  catch (SQLException ex) {
            System.err.println("Baza tapilmadi");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver tapilmadi");
        }finally{
            if(conn!=null) conn.close();
        }
        return names;
    }
    public Double productPrice(String name) throws SQLException{
        String query="select price from products where name=?";
        Double price=null;
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            pst.setString(1, name);
            ResultSet rst=pst.executeQuery();
            while(rst.next()){
                price=rst.getDouble("price");
            }
        }  catch (SQLException ex) {
            System.err.println("Baza tapilmadi");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver tapilmadi");
        }finally{
            if(conn!=null) conn.close();
        }
        return price;
    }
    public Integer productStockCount(String name) throws SQLException{
        String query="select stock_count from products where name=? and existence=1";
        Integer stockCount=null;
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            pst.setString(1, name);
            ResultSet rst=pst.executeQuery();
            while(rst.next()){
                stockCount=rst.getInt("stock_count");
            }
        }  catch (SQLException ex) {
            System.err.println("Baza tapilmadi");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver tapilmadi");
        }finally{
            if(conn!=null) conn.close();
        }
        return stockCount;
    }
    public void updateProductStockCount(String name,Integer stockCount) throws SQLException{
        String query="update products set stock_count=? where name=?";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            pst.setInt(1, stockCount);
            pst.setString(2, name);
            ResultSet rst=pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SalesDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SalesDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conn!=null){
                conn.commit();
                conn.close();
            }
        }
        
    }
    public void insertSales(String sellerName,String buyerName,String productName,Integer count) throws SQLException{
        String seq="select salesSequence.nextval from dual";
        String query="insert into sales (id,seller_id,buyer_id,product_id,sales_count) values (?,"
                + "(select id from customers where name=?),(select id from customers where name=?),"
                + "(select id from products where name=?),?)";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(seq);
            ResultSet rstSeq=pst.executeQuery();
            Integer id=null;
            while(rstSeq.next())
            {
                id=rstSeq.getInt(1);
            }
            PreparedStatement ps=conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, sellerName);
            ps.setString(3, buyerName);
            ps.setString(4, productName);
            ps.setInt(5, count);
            ResultSet rs=ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SalesDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SalesDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conn!=null){
                conn.commit();
                conn.close();
            }
        }
    }
}
