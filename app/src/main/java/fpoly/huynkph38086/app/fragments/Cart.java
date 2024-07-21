package fpoly.huynkph38086.app.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fpoly.huynkph38086.app.models.Product;

public class Cart extends ListFrag {
    List<Product> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater, container, savedInstanceState);

        ibRefresh.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);

        list = new ArrayList<>();
        list.add(new Product("_id", "Sản phẩm mẫu", 200, 1, "", ""));

        return view;
    }
}