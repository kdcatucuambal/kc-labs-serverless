package com.kc.cloud.labs.aws.lambda.products;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.kc.cloud.labs.aws.models.app.Product;
import com.kc.cloud.labs.aws.services.ProductService;
import com.kc.cloud.labs.aws.utils.KcUtil;
import com.kc.cloud.models.ResponseObject;
import com.kc.cloud.util.ConvertDataUtil;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;

@Named("LabsProductsGETAll")
@Singleton
public class LabsProductsGETAll implements RequestStreamHandler {

    Logger logger = Logger.getLogger(LabsProductsGETAll.class.getName());

    private final ProductService productService;
    public LabsProductsGETAll() {
        logger.info("Loading Lambda handler " + this.getClass().getName());
        this.productService = new ProductService();
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        logger.info("Invoking lambda: LabsProductsGETAll");
        ResponseObject<List<Product>> responseObject = getResponse(productService.getAll());
        logger.info("Response Object: " + responseObject);
        String jsonResponse = ConvertDataUtil.serializeObject(responseObject);
        logger.info("Response: " + jsonResponse);
        String apiKey = KcUtil.getAiKeyValue();
        logger.info("API Key (Lambda): " + apiKey);
        outputStream.write(jsonResponse.getBytes());
    }

    public ResponseObject<List<Product>> getResponse(List<Product> products) {
        ResponseObject<List<Product>> response = new ResponseObject<>();
        response.setStatusCode(200);
        response.setBody(products);
        return response;
    }
}
