package com.mercadolibre.ingrid_bicudo.util.factory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.ingrid_bicudo.model.Batch;
import com.mercadolibre.ingrid_bicudo.model.Product;


public class BatchFactory {
    static public List<Batch> createBatchsListAlface(){
        Product product0 = new Product();
        product0.setName("Alface");
        product0.setPrice(10D);
        product0.setCategory("RS");

        Batch batch0 = new Batch();
        batch0.setDueDate(LocalDate.of(10, 10, 11));
        batch0.setCurrentQuantity(100);
        batch0.setProduct(product0);

        Batch batch1 = new Batch();
        batch1.setDueDate(LocalDate.of(10, 10, 12));
        batch1.setCurrentQuantity(140);
        batch1.setProduct(product0);

        Batch batch2 = new Batch();
        batch2.setDueDate(LocalDate.of(10, 10, 13));
        batch2.setCurrentQuantity(150);
        batch2.setProduct(product0);
        
        List<Batch> batches = new ArrayList<Batch>();

        batches.add(batch0);
        batches.add(batch1);
        batches.add(batch2);

        return batches;
    }

    static public List<Batch> createBatchsListLeite(){
        Product product0 = new Product();
        product0.setName("Leite");
        product0.setPrice(10D);
        product0.setCategory("RF");

        Batch batch0 = new Batch();
        batch0.setDueDate(LocalDate.of(10, 10, 11));
        batch0.setCurrentQuantity(100);
        batch0.setProduct(product0);

        Batch batch1 = new Batch();
        batch1.setDueDate(LocalDate.of(10, 10, 12));
        batch1.setCurrentQuantity(140);
        batch1.setProduct(product0);

        Batch batch2 = new Batch();
        batch2.setDueDate(LocalDate.of(10, 10, 13));
        batch2.setCurrentQuantity(150);
        batch2.setProduct(product0);
        
        List<Batch> batches = new ArrayList<Batch>();

        batches.add(batch0);
        batches.add(batch1);
        batches.add(batch2);

        return batches;
    }


    static public List<Batch> createBatchsListBatata(){
        Product product0 = new Product();
        product0.setName("Batata");
        product0.setPrice(10D);
        product0.setCategory("FR");

        Batch batch0 = new Batch();
        batch0.setDueDate(LocalDate.of(10, 10, 11));
        batch0.setCurrentQuantity(100);
        batch0.setProduct(product0);

        Batch batch1 = new Batch();
        batch1.setDueDate(LocalDate.of(10, 10, 12));
        batch1.setCurrentQuantity(140);
        batch1.setProduct(product0);

        Batch batch2 = new Batch();
        batch2.setDueDate(LocalDate.of(10, 10, 13));
        batch2.setCurrentQuantity(150);
        batch2.setProduct(product0);
        
        List<Batch> batches = new ArrayList<Batch>();

        batches.add(batch0);
        batches.add(batch1);
        batches.add(batch2);

        return batches;
    }
}
