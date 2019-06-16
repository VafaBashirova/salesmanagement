/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.com.salesmanagement.dao;

import az.com.salesmanagement.db.DbConfig;
import az.com.salesmanagement.model.Dictionary;
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
public class DictionaryDao {
    private Connection conn=null;
    public List<String> dictonaryContents(String dic_key) throws SQLException{
        List <String> dictionaryList=new ArrayList<>();
        String query="select * from DICTIONARY where dic_key=?";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            pst.setString(1, dic_key);
            ResultSet resultSet=pst.executeQuery();
            while(resultSet.next()){
//                Integer id=resultSet.getInt("ID");
                String name=resultSet.getString("name");
//                Dictionary dictionary=new Dictionary(id,name);
                dictionaryList.add(name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DictionaryDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DictionaryDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conn!=null) conn.close();
        }
        return dictionaryList;
    }
    public Dictionary dictionaryById(String dicName){
        Dictionary dictionary=new Dictionary();
        String query="select * from dictionary where dic_name=?";
        try {
            conn=DbConfig.connection();
            PreparedStatement pst=conn.prepareStatement(query);
            pst.setString(1, dicName);
            ResultSet resultSet=pst.executeQuery();
            while(resultSet.next()){
                Integer id=resultSet.getInt("ID");
                String name=resultSet.getString("name");
                String value=resultSet.getString("value");
                dictionary=new Dictionary(id,name,value);
            }
        } catch (Exception e) {
        }finally{
            if(conn!=null)
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DictionaryDao.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        return dictionary;
    }
}
