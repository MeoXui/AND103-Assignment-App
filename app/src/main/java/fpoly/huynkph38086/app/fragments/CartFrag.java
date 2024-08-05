package fpoly.huynkph38086.app.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fpoly.huynkph38086.app.models.Fruit;

public class CartFrag extends ListFrag {
    List<Fruit> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater, container, savedInstanceState);
        tvTitle.setText("Giỏ hàng");
        ibRefresh.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);

        list = new ArrayList<>();

        return view;
    }
}