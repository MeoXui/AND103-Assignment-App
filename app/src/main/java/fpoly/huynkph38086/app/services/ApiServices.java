package fpoly.huynkph38086.app.services;

import java.util.ArrayList;
import java.util.Map;

import fpoly.huynkph38086.app.models.Distributor;
import fpoly.huynkph38086.app.models.Fruit;
import fpoly.huynkph38086.app.models.Response;
import fpoly.huynkph38086.app.models.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    static String BASE_URL = "http://10.24.39.155:3000/api/";

    //GET
    @GET("users")
    Call<Response<ArrayList<User>>> getUsers();

    @GET("distributors")
    Call<Response<ArrayList<Distributor>>> getDistributors();

    @GET("fruits")
    Call<Response<ArrayList<Fruit>>> getFruits(@Header("auth") String token);

    //GET search
    @GET("search_distributors")
    Call<Response<ArrayList<Distributor>>> searchDistributors(@Query("key") String key);

    @GET("search_fruits")
    Call<Response<ArrayList<Fruit>>> searchFruits(@Query("key") String key);

    //POST
    @Multipart
    @POST("register")
    Call<Response<User>> register(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password,
            @Part("email") RequestBody email,
            @Part("name") RequestBody name,
            @Part MultipartBody.Part avatar);

    @POST("login")
    Call<Response<User>> login(@Body User user);

    @POST("add_distributor")
    Call<Response<Distributor>> addDistributor(@Body Distributor distributor);

    @Multipart
    @POST("add_fruit")
    Call<Response<Fruit>> addFruit(
            @PartMap Map<String, RequestBody> requestBodyMap,
            @Part ArrayList<MultipartBody.Part> images);

    //PUT
    @PUT("update_distributor_id{id}")
    Call<Response<Distributor>> updateDistributors(@Path("id") String id, @Body Distributor distributor);

    //DELETE
    @DELETE("delete_distributor_id{id}")
    Call<Response<Distributor>> deleteDistributors(@Path("id") String id);
}
