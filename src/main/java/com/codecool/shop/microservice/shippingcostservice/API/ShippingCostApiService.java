package com.codecool.shop.microservice.shippingcostservice.API;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ShippingCostApiService {

    private static final String SERVICE_URL = "http://localhost:65011/shipping-cost";
    private static final String ORIGIN_ADDRESS = "Nagymező 44 Budapest";

    private static ShippingCostApiService INSTANCE;

    public static ShippingCostApiService getINSTANCE() {
        if (INSTANCE == null){
            INSTANCE = new ShippingCostApiService();
        }
        return INSTANCE;
    }

    public JSONObject getShippingCost(String destination) throws URISyntaxException, IOException {
        URIBuilder builder = new URIBuilder(SERVICE_URL).addParameter("origin", ORIGIN_ADDRESS)
                .addParameter("destination", destination);
        String response = execute(builder.build());
        JSONObject rawData = new JSONObject(response);
        return rawData;
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }
}
