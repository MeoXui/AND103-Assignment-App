package fpoly.huynkph38086.app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fpoly.huynkph38086.app.R;

public class InfoFrag extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        view.findViewById(R.id.btn_out).setOnClickListener(v -> getActivity().finish());

        view.findViewById(R.id.tv_distributors).setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().replace(R.id.fr, new DistributorFrag()).commit();
        });

        return view;
    }
}