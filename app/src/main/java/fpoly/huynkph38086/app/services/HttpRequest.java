package fpoly.huynkph38086.app.services;

import static fpoly.huynkph38086.app.services.APIServices.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequest {
    public APIServices api;

    public HttpRequest() {
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(APIServices.class);
    }
}
