package com.kc.cloud.labs.aws.services;

import com.kc.cloud.labs.aws.models.app.Product;
import com.kc.cloud.labs.aws.utils.DynamoDb;
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

    public List<Product> getAll(){
        SdkIterable<Product> products = table.scan().items();
        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            productList.add(product);
        }

        return productList;
    }

    public Product getById(String id){
        Key key = Key.builder().partitionValue(id).build();
        return table.getItem(key);
    }

    public boolean saveOrUpdate(Product product){
        try {
            table.putItem(product);
            return true;
        }
        catch (Exception e){
            logger.severe(e.getMessage());
            return false;
        }
    }

    public boolean deleteById(String id){
        try {
            Key key = Key.builder().partitionValue(id).build();
            table.deleteItem(key);
            return true;
        }
        catch (Exception e){
            logger.severe(e.getMessage());
            return false;
        }
    }

}
