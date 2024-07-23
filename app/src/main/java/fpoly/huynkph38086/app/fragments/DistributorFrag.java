package fpoly.huynkph38086.app.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fpoly.huynkph38086.app.adapters.DistributorAdapter;
import fpoly.huynkph38086.app.models.Distributor;
import fpoly.huynkph38086.app.models.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class DistributorFrag extends ListFrag {
    List<Distributor> list;
    DistributorAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        tvTitle.setText("Nhà phân phối");
        btnPay.setVisibility(View.GONE);

        list = new ArrayList<>();
        refresh();

        ibRefresh.setOnClickListener(v -> refresh());

        edSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String key = edSearch.getText().toString();
                request.api.searchDistributors(key).enqueue(callback);
                return true;
            }
            return false;
        });

        fab.setOnClickListener(v -> openDialog());

        return view;
    }

    void refresh() {
        request.api.getDistributors().enqueue(callback);
        edSearch.setText("");
        Toast.makeText(getActivity(), "Size: " + list.size(), Toast.LENGTH_SHORT).show();
    }

    void openDialog() {
    }

    Callback<Response<ArrayList<Distributor>>> callback = new Callback<Response<ArrayList<Distributor>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Distributor>>> call, retrofit2.Response<Response<ArrayList<Distributor>>> response) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().status == 200) {
                    list = response.body().data;
                    adapter = new DistributorAdapter(getActivity(), list);
                    lv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                Toast.makeText(getActivity(), response.body().mess, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Distributor>>> call, Throwable t) {
            Log.d("Get Data: ", t.getMessage());
        }
    };
}
