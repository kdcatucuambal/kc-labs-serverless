package com.kc.cloud.labs.aws.lambda.products;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kc.cloud.labs.aws.models.app.Product;
import com.kc.cloud.labs.aws.services.ProductService;
import com.kc.cloud.models.RequestObject;
import com.kc.cloud.models.ResponseObject;
import com.kc.cloud.util.ConvertDataUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

public class LabsProductsPST implements RequestStreamHandler {
    Logger logger = Logger.getLogger(LabsProductsPST.class.getName());
    private final ProductService productService;

    public LabsProductsPST() {
        this.productService = new ProductService();
        logger.info("Loading Lambda handler " + this.getClass().getName());
    }
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        String request = ConvertDataUtil.convertInputStreamToString(inputStream);
        logger.info("Request: " + request);
        RequestObject<Product> requestObject =  ConvertDataUtil.deserializeObject(request, new TypeReference<>() {});
        boolean isSaved = productService.saveOrUpdate(requestObject.getBody());
        String jsonResponse = ConvertDataUtil.serializeObject(getResponse(isSaved));
        logger.info("Response json: " + jsonResponse);
        outputStream.write(jsonResponse.getBytes());
    }

    public ResponseObject<Boolean> getResponse(Boolean isSaved) {
        ResponseObject<Boolean> response = new ResponseObject<>();
        response.setStatusCode(201);
        response.setBody(isSaved);
        return response;
    }

}
