package fpoly.huynkph38086.app.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fpoly.huynkph38086.app.R;
import fpoly.huynkph38086.app.adapters.ItemHandle;
import fpoly.huynkph38086.app.services.HttpRequest;

public class ListFrag<T> extends Fragment {
    EditText edSearch;
    ImageButton ibRefresh;
    TextView tvTitle;
    ListView lv;
    FloatingActionButton fab;
    Button btnPay;

    Dialog dialog;
    Activity activity;
    Context context;
    HttpRequest request;
    ItemHandle<T> handle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        edSearch = view.findViewById(R.id.ed_search);
        ibRefresh = view.findViewById(R.id.ib_re);
        tvTitle = view.findViewById(R.id.tv_title);
        lv = view.findViewById(R.id.lv);
        fab = view.findViewById(R.id.fab);
        btnPay = view.findViewById(R.id.btn_pay);

        activity = getActivity();
        context = activity;
        request = new HttpRequest();

        return view;
    }
}