package com.mercadolibre.ingrid_bicudo.util.factory;

import com.mercadolibre.ingrid_bicudo.model.Product;


public class ProductFactory {
    static public Product getMockedBatata(){
        Product batata =new Product();
        batata.setName("batata");
        batata.setPrice(10D);
        batata.setCategory("FS");
        return batata;
    }    
    static public Product getMockedAlface(){
        Product alface =new Product();
        alface.setName("Alface");
        alface.setPrice(10D);
        alface.setCategory("FS");
        return alface;
    }    

    static public Product getMockedLeite(){
        Product leite = new Product();
        leite.setName("Leite");
        leite.setPrice(10D);
        leite.setCategory("FS");
        return leite;
    }
}
