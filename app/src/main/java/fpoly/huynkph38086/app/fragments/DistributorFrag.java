package fpoly.huynkph38086.app.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import fpoly.huynkph38086.app.R;
import fpoly.huynkph38086.app.adapters.DistributorAdapter;
import fpoly.huynkph38086.app.adapters.ItemHandle;
import fpoly.huynkph38086.app.models.Distributor;
import fpoly.huynkph38086.app.models.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class DistributorFrag extends ListFrag<Distributor> {
    List<Distributor> list;
    DistributorAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        tvTitle.setText("Nhà phân phối");
        btnPay.setVisibility(View.GONE);

        list = new ArrayList<>();
        handle = new ItemHandle<Distributor>() {
            @Override
            public void delete(String id) {
                deleteDialog(id);
            }

            @Override
            public void update(Distributor old) {
                openDialog(old);
            }
        };
        refresh();

        ibRefresh.setOnClickListener(v -> refresh());

        edSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String key = edSearch.getText().toString();
                request.api.searchDistributors(key).enqueue(callbackArray);
                return true;
            }
            return false;
        });

        fab.setOnClickListener(v -> openDialog(null));

        return view;
    }

    void refresh() {
        request.api.getDistributors().enqueue(callbackArray);
        edSearch.setText("");
    }

    void openDialog(Distributor old) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_distributor);

        EditText edName = dialog.findViewById(R.id.ed_name);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel),
                btnSave = dialog.findViewById(R.id.btn_save);

        if (old != null) edName.setText(old.name);

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnSave.setOnClickListener(v -> {
            String name = edName.getText().toString();
            Distributor anew = new Distributor();
            anew.name = name;
            if (old == null) request.api.addDistributors(anew).enqueue(callback);
            else request.api.updateDistributors(old._id, anew).enqueue(callback);
            dialog.dismiss();
        });

        dialog.show();
    }

    public void deleteDialog(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Có", (dialog, which) -> {
            request.api.deleteDistributors(id).enqueue(callback);
            dialog.cancel();
        });
        builder.setNegativeButton("Không", (dialog, which) -> dialog.cancel());

        AlertDialog alert = builder.create();
        alert.show();
    }

    Callback<Response<Distributor>> callback = new Callback<Response<Distributor>>() {
        @Override
        public void onResponse(@NonNull Call<Response<Distributor>> call, retrofit2.Response<Response<Distributor>> response) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().status == 200) refresh();
                //Toast.makeText(context, response.body().mess, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(@NonNull Call<Response<Distributor>> call, Throwable t) {
            Log.d("Get Data: ", t.getMessage());
        }
    };

    Callback<Response<ArrayList<Distributor>>> callbackArray = new Callback<Response<ArrayList<Distributor>>>() {
        @Override
        public void onResponse(@NonNull Call<Response<ArrayList<Distributor>>> call, retrofit2.Response<Response<ArrayList<Distributor>>> response) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().status == 200) {
                    list = response.body().data;
                    adapter = new DistributorAdapter(context, list, handle);
                    lv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                //Toast.makeText(context, response.body().mess, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(@NonNull Call<Response<ArrayList<Distributor>>> call, Throwable t) {
            Log.d("Get Data: ", t.getMessage());
        }
    };
}
