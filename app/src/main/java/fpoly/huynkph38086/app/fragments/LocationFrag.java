package fpoly.huynkph38086.app.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import fpoly.huynkph38086.app.R;
import fpoly.huynkph38086.app.spinner.LocationSpinner;
import fpoly.huynkph38086.app.models.District;
import fpoly.huynkph38086.app.models.Province;
import fpoly.huynkph38086.app.models.Ward;
import fpoly.huynkph38086.app.services.GHNRequest;
import fpoly.huynkph38086.app.services.ResponseGHN;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationFrag extends Fragment {
    Activity activity;
    Context context;
    GHNRequest request;

    Spinner spProvince, spDistrict, spWard;
    EditText edLocation;
    Button btnNext;

    int WardCode;
    int DistrictID;
    int ProvinceID;
    String sLocation;

    LocationSpinner<Ward> wardSpinner;
    LocationSpinner<District> districtSpinner;
    LocationSpinner<Province> provinceSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        activity = getActivity();
        context = activity;
        request = new GHNRequest();

        spProvince = view.findViewById(R.id.sp_province);
        spDistrict = view.findViewById(R.id.sp_district);
        spWard = view.findViewById(R.id.sp_ward);
        edLocation = view.findViewById(R.id.ed_location);
        btnNext = view.findViewById(R.id.btn_next);

        request.services.getProvinces().enqueue(callbackP);

        return view;
    }

    Callback<ResponseGHN<ArrayList<Province>>> callbackP = new Callback<ResponseGHN<ArrayList<Province>>>() {
        @Override
        public void onResponse(Call<ResponseGHN<ArrayList<Province>>> call, Response<ResponseGHN<ArrayList<Province>>> response) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().code == 200) {
                    provinceSpinner = new LocationSpinner<>(context, response.body().data);
                    spProvince.setAdapter(provinceSpinner);
                    spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ProvinceID = ((Province) parent.getAdapter().getItem(position)).ProvinceID;
                            sLocation = ((Province) parent.getAdapter().getItem(position)).ProvinceName;
                            request.services.getDistricts(ProvinceID).enqueue(callbackD);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseGHN<ArrayList<Province>>> call, Throwable t) {

        }
    };


    Callback<ResponseGHN<ArrayList<District>>> callbackD = new Callback<ResponseGHN<ArrayList<District>>>() {
        @Override
        public void onResponse(Call<ResponseGHN<ArrayList<District>>> call, Response<ResponseGHN<ArrayList<District>>> response) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().code == 200) {
                    districtSpinner = new LocationSpinner<>(context, response.body().data);
                    spDistrict.setAdapter(districtSpinner);
                    spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            DistrictID = ((District) parent.getAdapter().getItem(position)).DistrictID;
                            sLocation = ((District) parent.getAdapter().getItem(position)).DistrictName + ", " + sLocation;
                            request.services.getWards(DistrictID).enqueue(callbackW);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseGHN<ArrayList<District>>> call, Throwable t) {

        }
    };

    Callback<ResponseGHN<ArrayList<Ward>>> callbackW = new Callback<ResponseGHN<ArrayList<Ward>>>() {
        @Override
        public void onResponse(Call<ResponseGHN<ArrayList<Ward>>> call, Response<ResponseGHN<ArrayList<Ward>>> response) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().code == 200) {
                    wardSpinner = new LocationSpinner<>(context, response.body().data);
                    spWard.setAdapter(wardSpinner);
                    spWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            WardCode = ((Ward) parent.getAdapter().getItem(position)).WardCode;
                            sLocation = ((Ward) parent.getAdapter().getItem(position)).WardName + ", " + sLocation;
                            edLocation.setText(sLocation);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseGHN<ArrayList<Ward>>> call, Throwable t) {

        }
    };
}