package inventory.service;
import inventory.model.AbstractPart;
import inventory.model.InhousePart;
import inventory.model.Inventory;
import inventory.model.Product;
import inventory.model.exception.InvalidProductException;
import inventory.repository.ProductAndPartsRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WBT_Testing {

    private InventoryService inventoryService;
    private ProductAndPartsRepository repository;
    private Inventory inventory;

    private InventoryService inventoryService1;
    private ProductAndPartsRepository repository1;
    private Inventory inventory1;
    private ObservableList<AbstractPart> addParts = FXCollections.observableArrayList(new InhousePart(1, "1", 2, 15, 10, 20, 1));

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        repository = new ProductAndPartsRepository(inventory);
        inventoryService = new InventoryService(repository);

        try {
            inventoryService.addProduct("cuie", 11.2, 5, 1, 10000, addParts);
            inventoryService.addProduct("ciment", 11.2, 5, 1, 10000, addParts);
            inventoryService.addProduct("var", 11.2, 5, 1, 1000, addParts);
            inventoryService.addProduct("p4", 11.2, 5, 1, 1000, addParts);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void test1() {
        assertEquals(inventoryService.lookupProduct("cuie").getProductId(), 1);
    }

    @Test
    void test2() {
        assertEquals(inventoryService.lookupProduct("2").getProductId(), 2);
    }

    @Test
    void test3() {
        assertEquals(inventoryService.lookupProduct("var").getProductId(), 3);
    }

    @Test
    void test4() {
        inventory1 = new Inventory();
        repository1 = new ProductAndPartsRepository(inventory1);
        inventoryService1 = new InventoryService(repository1);

        assertEquals(inventoryService1.lookupProduct("alabala"), new Product(0, null, 0.0, 0, 0, 0, null));
    }


    @Test
    void test5() {
        assertEquals(inventoryService.lookupProduct("alabala"), null);
    }


}
