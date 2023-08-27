package com.kc.cloud.labs.aws.lambda.products;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kc.cloud.labs.aws.models.app.Product;
import com.kc.cloud.labs.aws.services.ProductService;
import com.kc.cloud.labs.aws.utils.KcUtil;
import com.kc.cloud.models.RequestObject;
import com.kc.cloud.models.ResponseObject;
import com.kc.cloud.util.ConvertDataUtil;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Logger;

@Named("LabsProductsGETById")
@Singleton
public class LabsProductsGETById implements RequestStreamHandler {

    Logger logger = Logger.getLogger(LabsProductsGETById.class.getName());
    private final ProductService productService;

    public LabsProductsGETById() {
        this.productService = new ProductService();
        logger.info("Loading Lambda handler " + this.getClass().getName());
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        String request = ConvertDataUtil.convertInputStreamToString(inputStream);
        logger.info("Request: " + request);
        RequestObject<?> requestObject =  ConvertDataUtil.deserializeObject(request, new TypeReference<>() {});
        Map<String, String> params = requestObject.getGetbody().getParams();
        Product productFound = productService.getById(params.get("id"));
        String jsonResponse = ConvertDataUtil.serializeObject(getResponse(productFound));
        logger.info("Response json: " + jsonResponse);
        String apiKey = KcUtil.getAiKeyValue();
        logger.info("API Key (Lambda): " + apiKey);
        outputStream.write(jsonResponse.getBytes());
    }

    public ResponseObject<Product> getResponse(Product product) {
        ResponseObject<Product> response = new ResponseObject<>();
        response.setStatusCode(200);
        response.setBody(product);
        return response;
    }
}
