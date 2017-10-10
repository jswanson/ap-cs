
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SupremeParserApp;

import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

/**
 *
 * @author jswanson
 */
public class SupremeWebParser {
    
    public List<String> GetCurrentProducts() {
        
        List<String> products = new ArrayList<String>();
        
        try {                           
            Document doc = Jsoup.connect("http://www.supremenewyork.com/shop").get();
            Elements productElements = doc.select("ul#shop-scroller li a");
            
            for (Element productElement : productElements) {
                
                String productUrl = productElement.attr("href");
                String productId  = productUrl.substring(productUrl.lastIndexOf('/') + 1);
                
                products.add(productId);                
            }
        }
        catch(Exception ex) {
            ex.printStackTrace(); // for now, simply output it.
        }  
                        
        
        return products;
    }
    
    public ProductInfo GetProductInfo(String productId) {
        
        ProductInfo productInfo = new ProductInfo();
        
        try {      
                     
            Document doc = Jsoup.connect("http://www.supremenewyork.com/shop/" + productId).get();
            Element priceElement = doc.select("span[itemprop$=price]").first();
            Element nameElement = doc.select("h1[itemprop$=name]").first();
            Element isSoldOutElement = doc.select("b.sold-out").first();
            
            productInfo.ProductId = productId;
            productInfo.Price = Double.parseDouble(priceElement.html().replace("$",""));
            productInfo.Name = nameElement.html();
            
            if (isSoldOutElement == null) {
                productInfo.IsSoldOut = false;
            }
            else {
                productInfo.IsSoldOut = true;
            }
                       
        }
        catch(Exception ex) {
            ex.printStackTrace(); // for now, simply output it.
        }        
        
        return productInfo;
    }         
    
}
