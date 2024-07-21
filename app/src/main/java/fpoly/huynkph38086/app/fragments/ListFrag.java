package fpoly.huynkph38086.app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.huynkph38086.app.R;
import fpoly.huynkph38086.app.models.Product;
import fpoly.huynkph38086.app.models.Response;
import fpoly.huynkph38086.app.services.APIServices;
import fpoly.huynkph38086.app.services.HttpRequest;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListFrag extends Fragment {
    EditText edSearch;
    ImageButton ibRefresh;
    ListView lv;
    FloatingActionButton fab;
    Button btnPay;

    HttpRequest request;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        edSearch = view.findViewById(R.id.ed_search);
        ibRefresh = view.findViewById(R.id.ib_re);
        lv = view.findViewById(R.id.lv);
        fab = view.findViewById(R.id.fab);
        btnPay = view.findViewById(R.id.btn_pay);

        request = new HttpRequest();

        return view;
    }
}