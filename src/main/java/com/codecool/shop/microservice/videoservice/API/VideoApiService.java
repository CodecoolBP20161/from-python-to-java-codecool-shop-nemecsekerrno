package com.codecool.shop.microservice.videoservice.API;

import java.net.URI;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by shevah on 24/01/17.
 */
public class VideoApiService {
    private static final String SERVICE_URL = "http://localhost:60000";

    private static VideoApiService INSTANCE;

    public static VideoApiService getINSTANCE() {
        if (INSTANCE == null){
            INSTANCE = new VideoApiService();
        }
        return INSTANCE;
    }

    public String getVideo(String productName) throws URISyntaxException, IOException {
        String response = execute(new URIBuilder(SERVICE_URL + "/apivideos?search=" + productName).build());
        JSONArray rawData = new JSONArray(response);
        JSONObject review = new JSONObject(rawData.get(1).toString());
        String embedCode = review.getString("embed code");
        return embedCode;
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }

}
