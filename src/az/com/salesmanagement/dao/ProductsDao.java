/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.com.salesmanagement.dao;

import az.com.salesmanagement.db.DbConfig;
import az.com.salesmanagement.model.Products;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vafa
 */
public class ProductsDao {
    Connection conn=null;
    public List<Products> products() throws SQLException{
        List<Products> products=new ArrayList<>();
        String query="select * from products";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            ResultSet resultSet=pst.executeQuery();
            while(resultSet.next()){
                Integer id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                Double price=resultSet.getDouble("price");
                Integer stock_count=resultSet.getInt("stock_count");
                Integer existence=resultSet.getInt("existence");
                Products product=new Products(id, name, price, stock_count,existence);
                products.add(product);
            }
        } catch (Exception e) {
        }finally{
            if(conn!=null) conn.close();
        }
        return products;
    }
    public void insertProducts(String name,double price,Integer stockCount) throws SQLException{
        String query="insert into products (id,name,price,stock_count,existence) values (?,?,?,?,1)";
        String idSeq="select products_sequence.nextval from dual";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            PreparedStatement pSeq=conn.prepareStatement(idSeq);
            ResultSet r=pSeq.executeQuery();
            Integer id=null;
            while(r.next()){
                id=r.getInt(1);
            }
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setDouble(3, price);
            pst.setInt(4, stockCount);
            pst.execute();
        } catch (Exception e) {
        }finally{
            if(conn!=null){
                conn.commit();
                conn.close();
            }
        }
    }
    public void updateProducts(Integer id,String name,double price,Integer stockCount) throws SQLException{
        String query="update products set name=?, price=?, stock_count=? where id=?";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setDouble(2, price);
            pst.setInt(3, stockCount);
            pst.setInt(4, id);
            pst.execute();
        } catch (Exception e) {
        }finally{
            if(conn!=null){
                conn.commit();
                conn.close();
            }
        }
    }
    public List<Products> selectedProducts(String name,Double price,Integer stockCount) throws SQLException{
        List<Products> selectedProducts=new ArrayList<>();
        StringBuilder query=new StringBuilder("select * from products");
        if(name!=null || price!=null || stockCount!=null) query.append(" where");
        if(name!=null) query.append(" lower(name) like lower('").append(name).append("') || '%'");
        if(price!=null && name!=null) query.append(" and price=").append(price);
        else if(price!=null && name==null) query.append(" price=").append(price);
        if(stockCount!=null && (name!=null || price!=null)) query.append(" and stock_count=").append(stockCount);
        else if(stockCount!=null && name==null && price==null) query.append(" stock_count=").append(stockCount);
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(String.valueOf(query));
            ResultSet resultSet=pst.executeQuery();
            while(resultSet.next()){
                Integer id=resultSet.getInt("id");
                String rname=resultSet.getString("name");
                double rprice=resultSet.getDouble("price");
                Integer rstockCount=resultSet.getInt("stock_count");
                Integer existence=resultSet.getInt("existence");
                Products selectedProduct=new Products(id, rname, rprice, rstockCount,existence);
                selectedProducts.add(selectedProduct);
        }    
        } catch (Exception e) {
        }finally{
            if(conn!=null) conn.close();
        }
        return selectedProducts;
    }
    public void deleteProducts(String name,Double price,Integer stockCount) throws SQLException{
        String query="update PRODUCTS set existence=0 where  name=? and price=? and stock_count=?";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setDouble(2, price);
            pst.setInt(3, stockCount);
            pst.execute();
        } catch (Exception e) {
        }finally{
            if(conn!=null){
                conn.commit();
                conn.close();
            }
        }
    }
}
