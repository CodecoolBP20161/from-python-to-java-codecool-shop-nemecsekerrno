package com.codecool.shop.microservice.videoservice.API;

import java.net.URI;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
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

    public String getVideos(String productName) throws URISyntaxException, IOException {
        String response = execute(new URIBuilder(SERVICE_URL + "/apivideos?search=" +productName).build());
        return response;
    }

    private String execute(URI uri) throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();
    }

//    public static void main(String[] args) {
//        VideoApiService api = VideoApiService.getINSTANCE();
//        String result = "";
//        try {
//            result = api.getVideos("szelfibot");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(result);
//    }

}
