package fpoly.huynkph38086.app.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import fpoly.huynkph38086.app.R;
import fpoly.huynkph38086.app.adapters.HomeAdapter;
import fpoly.huynkph38086.app.hamdle.ItemHandle;
import fpoly.huynkph38086.app.models.Fruit;
import fpoly.huynkph38086.app.services.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class HomeFrag extends ListFrag<Fruit> {
    List<Fruit> list;
    HomeAdapter adapter;

    SharedPreferences sp;
    String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater, container, savedInstanceState);
        tvTitle.setText("Trái cây");
        btnPay.setVisibility(View.GONE);

        list = new ArrayList<>();
        sp = activity.getSharedPreferences("INFO", MODE_PRIVATE);
        token = sp.getString("token", "");
        handle = new ItemHandle<Fruit>() {
            @Override
            public void delete(String id) {
                //deleteDialog(id);
            }

            @Override
            public void update(Fruit old) {
                //openDialog(old);
            }
        };
        refresh();

        ibRefresh.setOnClickListener(v -> refresh());

        FragmentManager fm = getParentFragmentManager();

        lv.setOnItemClickListener((parent, v, position, id) -> {
            fm.beginTransaction().replace(R.id.fr, new DetailFruitFrag()).commit();
        });

        fab.setOnClickListener(v -> {
            fm.beginTransaction().replace(R.id.fr, new EditFruitFrag()).commit();
        });

        return view;
    }

    void refresh() {
        request.api.getFruits("Bearer " + token).enqueue(callback);
    }

    Callback<Response<ArrayList<Fruit>>> callback = new Callback<Response<ArrayList<Fruit>>>() {
        @Override
        public void onResponse(@NonNull Call<Response<ArrayList<Fruit>>> call, retrofit2.Response<Response<ArrayList<Fruit>>> response) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().status == 200) {
                    list = response.body().data;
                    adapter = new HomeAdapter(context, list, handle);
                    lv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<Response<ArrayList<Fruit>>> call, Throwable t) {
            Log.e("Get Data: ", t.getMessage());
        }
    };
}