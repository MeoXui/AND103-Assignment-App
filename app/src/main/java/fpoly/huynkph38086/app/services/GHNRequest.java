package fpoly.huynkph38086.app.services;

import static fpoly.huynkph38086.app.services.GHNServices.GHN_URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GHNRequest {
    public final String ShopId = "191612";
    public final String Token = "6bb50173-ee66-11ee-8bfa-8a2dda8ec551";
    public GHNServices services;

    public GHNRequest() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("ShopId", ShopId)
                    .addHeader("Token", Token)
                    .build();
            return chain.proceed(request);
        });

        services = new Retrofit.Builder()
                .baseUrl(GHN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build().create(GHNServices.class);
    }
}
