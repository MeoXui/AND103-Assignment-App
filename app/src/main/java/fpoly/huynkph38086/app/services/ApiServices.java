package fpoly.huynkph38086.app.services;

import java.util.ArrayList;

import fpoly.huynkph38086.app.models.Distributor;
import fpoly.huynkph38086.app.models.Fruit;
import fpoly.huynkph38086.app.models.Response;
import fpoly.huynkph38086.app.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    static String BASE_URL = "http://192.168.2.11:3000/api/";

    //GET
    @GET("users")
    Call<Response<ArrayList<User>>> getUsers();

    @GET("distributors")
    Call<Response<ArrayList<Distributor>>> getDistributors();

    @GET("fruits")
    Call<Response<ArrayList<Fruit>>> getFruits();

    //GET search
    @GET("search_distributors")
    Call<Response<ArrayList<Distributor>>> searchDistributors(@Query("key") String key);

    @GET("search_fruits")
    Call<Response<ArrayList<Fruit>>> searchFruits(@Query("key") String key);

    //POST
    //@POST("register")
    //@POST("login")

    @POST("add_distributor")
    Call<Response<Distributor>> addDistributors(@Body Distributor distributor);

    //PUT
    @PUT("update_distributor_id{id}")
    Call<Response<Distributor>> updateDistributors(@Path("id") String id, @Body Distributor distributor);

    //DELETE
    @DELETE("delete_distributor_id{id}")
    Call<Response<Distributor>> deleteDistributors(@Path("id") String id);
}
