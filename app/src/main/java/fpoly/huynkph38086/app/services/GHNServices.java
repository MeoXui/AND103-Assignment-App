package fpoly.huynkph38086.app.services;

import java.util.ArrayList;

import fpoly.huynkph38086.app.models.District;
import fpoly.huynkph38086.app.models.Province;
import fpoly.huynkph38086.app.models.Ward;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GHNServices {
    static String GHN_URL = "http://dev-online-gateway.ghn.vn/shiip/public-api/master-data/";

    @GET("province")
    Call<ResponseGHN<ArrayList<Province>>> getProvinces();

    @POST("district")
    Call<ResponseGHN<ArrayList<District>>> getDistricts(@Body Province province);

    @GET("ward")
    Call<ResponseGHN<ArrayList<Ward>>> getWards(@Query("district_id") int district_id);
}
