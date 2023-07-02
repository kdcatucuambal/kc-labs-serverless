package com.kc.cloud.labs.aws.lambda.products;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kc.cloud.labs.aws.models.app.Response;
import com.kc.cloud.labs.aws.models.request.RequestObject;
import com.kc.cloud.labs.aws.services.ProductService;
import com.kc.cloud.labs.aws.utils.KcUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Logger;

public class LabsProductsDEL implements RequestStreamHandler {
    Logger logger = Logger.getLogger(LabsProductsGETById.class.getName());
    private final ProductService productService;

    public LabsProductsDEL() {
        this.productService = new ProductService();
        logger.info("Loading Lambda handler " + this.getClass().getName());
    }
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        String request = KcUtil.convertInputStreamToString(inputStream);
        logger.info("Request: " + request);
        RequestObject<?> requestObject =  KcUtil.deserializeObject(request, new TypeReference<>() {});
        Map<String, String> params = requestObject.getGetbody().getParams();
        productService.deleteById(params.get("id"));
        String jsonResponse = KcUtil.serializeObject(getResponse(true));
        logger.info("Response json: " + jsonResponse);
        outputStream.write(jsonResponse.getBytes());
    }

    public Response<Boolean> getResponse(Boolean isSaved) {
        Response<Boolean> response = new Response<>();
        response.setStatusCode(200);
        response.setBody(isSaved);
        return response;
    }



}
