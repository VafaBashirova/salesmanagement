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
public class Products {
    private Integer id;
    private String name;
    private double price;
    private Integer stockCount;
    private Integer existence;

    public Integer getExistence() {
        return existence;
    }

    public void setExistence(Integer existence) {
        this.existence = existence;
    }

    public Products(Integer id, String name, double price, Integer stockCount, Integer existence) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockCount = stockCount;
        this.existence = existence;
    }

    public Products(Integer id, String name, double price, Integer stockCount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockCount = stockCount;
    }
    public Products(){
        
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    @Override
    public String toString() {
        return "Products{" + "id=" + id + ", name=" + name + ", price=" + price + ", stockCount=" + stockCount + '}';
    }
    
}