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
public class Customers {
    private Integer id;
    private String name;
    private String voen;
    private Integer type;
    private Integer existence;
    private String typeLabel;

    public Customers(Integer id, String name, String voen, Integer type) {
        this.id = id;
        this.name = name;
        this.voen = voen;
        this.type = type;
    }
    public Customers(Integer id, String name, String voen, String typeLabel) {
        this.id = id;
        this.name = name;
        this.voen = voen;
        this.typeLabel = typeLabel;
    }
    public Customers(Integer id, String name, String voen, Integer type,Integer existence) {
        this.id = id;
        this.name = name;
        this.voen = voen;
        this.type = type;
        this.existence=existence;
    }
    public Customers(Integer id, String name, String voen, String typeLabel,Integer existence) {
        this.id = id;
        this.name = name;
        this.voen = voen;
        this.typeLabel = typeLabel;
        this.existence=existence;
    }
    public Customers(){
        
    }

    public String getTypeLabel() {
        return typeLabel;
    }

    public void setTypeLabel(String typeLabel) {
        this.typeLabel = typeLabel;
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

    public String getVoen() {
        return voen;
    }

    public void setVoen(String voen) {
        this.voen = voen;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getExistence() {
        return existence;
    }

    public void setExistence(Integer existence) {
        this.existence = existence;
    }
    
    @Override
    public String toString() {
        return "Customers{" + "id=" + id + ", name=" + name + ", voen=" + voen + ", type=" + type + '}';
    }
    
}
