package fpoly.huynkph38086.app.fragments;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fpoly.huynkph38086.app.adapters.HomeAdapter;
import fpoly.huynkph38086.app.models.Product;
import fpoly.huynkph38086.app.models.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class Home extends ListFrag {
    List<Product> list;
    HomeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater, container, savedInstanceState);

        btnPay.setVisibility(View.GONE);

        list = new ArrayList<>();
        list.add(new Product("_id", "Sản phẩm mẫu", 200, 1, "", ""));

        refresh();

        ibRefresh.setOnClickListener(v -> refresh());

        return view;
    }

    void refresh() {
        request.api.getCars().enqueue(callback);
        adapter = new HomeAdapter(getActivity(), list);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    Callback<Response<ArrayList<Product>>> callback = new Callback<Response<ArrayList<Product>>>() {
        @Override
        public void onResponse(@NonNull Call<Response<ArrayList<Product>>> call, retrofit2.Response<Response<ArrayList<Product>>> response) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().status == 200) {
                    list.clear();
                    list.addAll(response.body().data);
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Product>>> call, Throwable t) {
            Log.e("Get Data: ", t.getMessage());
        }
    };
}