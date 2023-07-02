package com.kc.cloud.labs.aws.lambda.products;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kc.cloud.labs.aws.models.app.Product;
import com.kc.cloud.labs.aws.models.app.Response;
import com.kc.cloud.labs.aws.models.request.RequestObject;
import com.kc.cloud.labs.aws.services.ProductService;
import com.kc.cloud.labs.aws.utils.KcUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Logger;

public class LabsProductsGETById implements RequestStreamHandler {

    Logger logger = Logger.getLogger(LabsProductsGETById.class.getName());
    private final ProductService productService;

    public LabsProductsGETById() {
        this.productService = new ProductService();
        logger.info("Loading Lambda handler " + this.getClass().getName());
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        String request = KcUtil.convertInputStreamToString(inputStream);
        logger.info("Request: " + request);
        RequestObject<?> requestObject =  KcUtil.deserializeObject(request, new TypeReference<>() {});
        Map<String, String> params = requestObject.getGetbody().getParams();
        Product productFound = productService.getById(params.get("id"));
        String jsonResponse = KcUtil.serializeObject(getResponse(productFound));
        logger.info("Response json: " + jsonResponse);

        outputStream.write(jsonResponse.getBytes());
    }

    public Response<Product> getResponse(Product product) {
        Response<Product> response = new Response<>();
        response.setStatusCode(200);
        response.setBody(product);
        return response;
    }
}
