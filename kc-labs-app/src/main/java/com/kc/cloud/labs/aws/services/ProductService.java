package com.kc.cloud.labs.aws.services;

import com.kc.cloud.api.DynamoDb;
import com.kc.cloud.labs.aws.models.app.Product;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductService {

    private static final Logger logger = Logger.getLogger(ProductService.class.getName());
    private static final DynamoDbTable<Product> table = DynamoDb.getEnhancedClient()
            .table(System.getenv("PRODUCTS_TABLE"), TableSchema.fromBean(Product.class));

    public List<Product> getAll() {
        SdkIterable<Product> products = table.scan().items();
        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            productList.add(product);
        }
        return productList;
    }

    public Product getById(String id) {
        Key key = Key.builder().partitionValue(id).build();
        Product product = table.getItem(key);
        logger.info("Product info: " + product);
        if (product == null) {
            throw new RuntimeException("404");
        }
        return product;
    }

    public boolean saveOrUpdate(Product product) {
        try {
            table.putItem(product);
            return true;
        } catch (Exception e) {
            logger.severe(e.getMessage());
            throw new RuntimeException("409", new Throwable(e.getMessage()));
        }
    }

    public void deleteById(String id) {

        Key key = Key.builder().partitionValue(id).build();
        Product p = table.deleteItem(key);
        logger.info("Product deleted: " + p);
        if (p == null) {
            throw new RuntimeException("404");
        }

    }

}
