/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.com.salesmanagement.model;

/**
 *
 * @author Vafa
 */
public class Dictionary {
    private Integer id;
    private String name;
    private String value;
    private String dic_key;

    public Dictionary(Integer id, String name, String value, String dic_key) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.dic_key = dic_key;
    }

    public Dictionary(Integer id) {
        this.id = id;
    }
    
    public Dictionary(Integer id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
    public Dictionary(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Dictionary(String name){
        this.name=name;
    }
    public Dictionary(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDic_key() {
        return dic_key;
    }

    public void setDic_key(String dic_key) {
        this.dic_key = dic_key;
    }
    @Override
    public String toString() {
        return name;
    }
    
}
