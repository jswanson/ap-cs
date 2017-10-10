/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SupremeParserApp;

import java.util.*;

/**
 *
 * @author jswanson
 */
public class SupremeParserApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        
        SupremeWebParser parser = new SupremeWebParser();

        List<String> products = parser.GetCurrentProducts();
        
        for (String productId : products) {
    
            DisplayProductInfo(productId);
        }
                            
    }
    
    public static void DisplayProductInfo(String productId){
        SupremeWebParser parser = new SupremeWebParser();
        
        ProductInfo productInfo = parser.GetProductInfo(productId);
        
        System.out.println("\nProductId: " + productInfo.ProductId);
        System.out.println("   Name: " + productInfo.Name);
        System.out.println("   Sold Out: " + productInfo.IsSoldOut);
        System.out.println("   Price: $" + productInfo.Price);
    }
    
}
