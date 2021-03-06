package it.giorgiaauroraadorni.booktique.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ItemFactory implements EntityFactory<Item> {
    @Autowired
    private EntityFactory<Book> bookFactory;

    @Autowired
    private EntityFactory<Supplier> supplierFactory;

    @Override
    public Item createValidEntity(int idx) {
        var item = new Item();
        var book = bookFactory.createValidEntity(idx);
        var supplier = supplierFactory.createValidEntity(idx);

        // attributes
        item.setUnitPrice(BigDecimal.valueOf(13.49));
        item.setQuantityPerUnit(1);

        // associations
        item.setBookItem(book);
        item.setSupplier(supplier);

        return item;
    }
}
