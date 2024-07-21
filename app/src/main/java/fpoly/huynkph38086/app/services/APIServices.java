package fpoly.huynkph38086.app.services;

import java.util.ArrayList;

import fpoly.huynkph38086.app.models.Product;
import fpoly.huynkph38086.app.models.Response;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServices {
    static String BASE_URL = "http://192.168.2.11:3000/";

    @GET("api/fruits")
    Call<Response<ArrayList<Product>>> getFruits();

    @GET("api/cars")
    Call<Response<ArrayList<Product>>> getCars();

    //@POST("/api/register")
}
