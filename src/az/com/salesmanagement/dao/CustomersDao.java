/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.com.salesmanagement.dao;
import az.com.salesmanagement.db.DbConfig;
import az.com.salesmanagement.model.Customers;
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
public class CustomersDao {
    private Connection conn=null;
    
    public List<Customers> customers() throws SQLException{
        List<Customers> customers=new ArrayList<>();
        String query="select * from customers";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            ResultSet resultSet=pst.executeQuery();
            while(resultSet.next()){
                Integer id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String voen=resultSet.getString("voen");
                Integer type=resultSet.getInt("type");
                Integer existence=resultSet.getInt("existence");
                Customers customer=new Customers(id, name, voen, type,existence);
                customers.add(customer);
            }
            
        } catch (SQLException ex) {
            System.err.println("Baza tapilmadi");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver tapilmadi");
        }finally{
            if(conn!=null) conn.close();
        }
        return customers;
    }
    public List<Customers> selectedCustomers(String name,String voen, Integer type) throws SQLException{
        List<Customers> selectedCustomers=new ArrayList<>();
        StringBuilder query=new StringBuilder("select c.id,c.name,c.voen,d.name as type,c.existence from CUSTOMERS c inner join dictionary d on c.type=D.ID");
        if(name!=null || voen!=null || type!=null) query.append(" where");
        if(name!=null) query.append(" lower(c.name) like  '%' || lower('").append(name).append("') ||'%'");
        if(voen!=null && name!=null) query.append(" and lower(c.voen) like lower('").append(voen).append("') || '%'");
        else if(voen!=null && name==null) query.append(" lower(c.voen) like lower('").append(voen).append("') || '%'");
        if(type!=null && (voen!=null || name!=null)) if(voen!=null && name!=null) query.append(" and d.id=").append(type);
        else if(type!=null && voen==null && name==null) query.append(" d.id=").append(type);
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(String.valueOf(query));
            ResultSet resultSet=pst.executeQuery();
            while(resultSet.next()){
                Integer id=resultSet.getInt("id");
                String rname=resultSet.getString("name");
                String rvoen=resultSet.getString("voen");
                String rtypeLabel=resultSet.getString("type");
                Integer rexistence=resultSet.getInt("existence");
                Customers selectedcustomer=new Customers(id, rname, rvoen, rtypeLabel,rexistence);
                selectedCustomers.add(selectedcustomer);
            }
            
        } catch (SQLException ex) {
            System.err.println("Baza tapilmadi");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver tapilmadi");
        }finally{
            if(conn!=null) conn.close();
        }
        return selectedCustomers;
    }
    public Customers addCustomer(Customers customer) throws SQLException{
        String query="insert into CUSTOMERS values(?,?,?,?,1)";
        String idSeq="select customer_sequence.nextval from dual";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            PreparedStatement pSeq=conn.prepareStatement(idSeq);
            ResultSet r=pSeq.executeQuery();
            Integer id=null;
            while(r.next()){
                id=r.getInt(1);
            }
            pst.setInt(1,id);
            pst.setString(2, customer.getName());
            pst.setString(3, customer.getVoen());
            pst.setInt(4, customer.getType());
            pst.execute();
            
        } catch (SQLException ex) {
            System.out.println("Baza tapilmadi.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver tapilmadi.");
        }finally{
            if(conn!=null){
                conn.commit();
                conn.close();
            }
        }
        return customer;
    }
    public void updateCustomerName(String name,int id) throws SQLException{
        String query="update CUSTOMERS set name=? where id=?";
        
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setInt(2, id);
            pst.execute();
            
        } catch (SQLException ex) {
            System.out.println("Baza tapilmadi.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver tapilmadi.");
        }finally{
            if(conn!=null) {
                conn.commit();
                conn.close();
            }
        }
    }
    public List<Customers> customersTableContents() throws SQLException{
        List costumersTableContents=new ArrayList();
        String query="select c.id,c.name,c.voen,d.name as type,c.existence from CUSTOMERS c inner join dictionary d on c.type=D.ID";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            ResultSet resultSet=pst.executeQuery();
            while(resultSet.next()){
                Integer id=resultSet.getInt("id");
                String name=resultSet.getString("Name");
                String voen=resultSet.getString("voen");
                String typeLabel=resultSet.getString("type");
                Integer existence=resultSet.getInt("existence");
                Customers customer=new Customers(id, name, voen, typeLabel,existence);
                costumersTableContents.add(customer);
            }
        } catch (SQLException ex) {
            System.out.println("Bazaya qosulmadi!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver tapilmadi!");
        }finally{
            if(conn!=null) conn.close();
        }
        return costumersTableContents;
    }
    public void deleteCustomer(Integer id,String name,String voen,Integer type) throws SQLException{
        String query="update CUSTOMERS set existence=0 where id=? and name=? and voen=? and type=?";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, voen);
            pst.setInt(4, type);
            pst.executeQuery();
        } catch (Exception e) {
        }finally{
            if(conn!=null){
                conn.commit();
                conn.close();
            }
        }
    }
    public void updateCustomer(Integer id,String name,String voen,Integer type) throws SQLException{
        String query="update CUSTOMERS set name=? , voen=? ,type=? where id=?";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, voen);
            pst.setInt(3, type);
            pst.setInt(4, id);
            pst.executeQuery();
        } catch (Exception e) {
        }finally{
            if(conn!=null){
                conn.commit();
                conn.close();
            }
        }
    }
}
