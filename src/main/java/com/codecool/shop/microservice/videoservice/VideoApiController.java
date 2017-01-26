package com.codecool.shop.microservice.videoservice;

import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.microservice.videoservice.API.VideoApiService;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by shevah on 24/01/17.
 */
public class VideoApiController {
    private final VideoApiService apiService;

    public VideoApiController(VideoApiService apiService) {
        this.apiService = apiService;
    }

    public String getVideo(Request request, Response response) throws SQLException, IOException, URISyntaxException {
        Integer prodId = Integer.parseInt(request.queryParams("id"));
        Product prod = new ProductDaoJdbc().find(prodId);
        response.type("text/xml");
        response.body(apiService.getVideo(prod.getName()));
        System.out.println(apiService.getVideo(prod.getName()));
        return apiService.getVideo(prod.getName());
    }

}
