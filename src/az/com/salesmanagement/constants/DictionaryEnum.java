/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.com.salesmanagement.constants;

/**
 *
 * @author Vafa
 */
public enum DictionaryEnum {
    CUSTOMER_TYPE("customer_type");
    private String key;

    DictionaryEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
    
}
