package fpoly.huynkph38086.app.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import fpoly.huynkph38086.app.R;
import fpoly.huynkph38086.app.adapters.CartAdapter;
import fpoly.huynkph38086.app.hamdle.ItemHandle;
import fpoly.huynkph38086.app.models.Fruit;

public class CartFrag extends ListFrag<Fruit> {
    List<Fruit> list;
    CartAdapter adapter;

    SharedPreferences sp;
    String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater, container, savedInstanceState);
        tvTitle.setText("Giỏ hàng");
        ibRefresh.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);

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

        btnPay.setOnClickListener(v -> {
            fm.beginTransaction().replace(R.id.fr, new LocationFrag()).commit();
        });

        return view;
    }

    void refresh() {}
}