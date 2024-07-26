package fpoly.huynkph38086.app.fragments;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fpoly.huynkph38086.app.adapters.HomeAdapter;
import fpoly.huynkph38086.app.models.Fruit;
import fpoly.huynkph38086.app.models.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class HomeFrag extends ListFrag {
    List<Fruit> list;
    HomeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater, container, savedInstanceState);
        btnPay.setVisibility(View.GONE);

        list = new ArrayList<>();

        refresh();

        ibRefresh.setOnClickListener(v -> refresh());

        return view;
    }

    void refresh() {
        request.api.getFruits().enqueue(callback);
        adapter = new HomeAdapter(getActivity(), list);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    Callback<Response<ArrayList<Fruit>>> callback = new Callback<Response<ArrayList<Fruit>>>() {
        @Override
        public void onResponse(@NonNull Call<Response<ArrayList<Fruit>>> call, retrofit2.Response<Response<ArrayList<Fruit>>> response) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().status == 200) {
                    list.clear();
                    list.addAll(response.body().data);
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Fruit>>> call, Throwable t) {
            Log.e("Get Data: ", t.getMessage());
        }
    };
}