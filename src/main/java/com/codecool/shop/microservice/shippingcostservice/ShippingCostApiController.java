package com.codecool.shop.microservice.shippingcostservice;

import com.codecool.shop.microservice.shippingcostservice.API.ShippingCostApiService;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;

public class ShippingCostApiController {
    private final ShippingCostApiService apiService;

    public ShippingCostApiController(ShippingCostApiService apiService) { this.apiService = apiService; }

    public JSONObject getShippingCost(Request request, Response response) throws IOException, URISyntaxException {
        String destination = request.queryParams("address");
        response.type("application/json");
        JSONObject toReturn = apiService.getShippingCost(destination);
        return toReturn;
    }
}
