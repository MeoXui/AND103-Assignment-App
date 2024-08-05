package fpoly.huynkph38086.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fpoly.huynkph38086.app.R;
import fpoly.huynkph38086.app.models.Distributor;

public class DistributorSpinner extends ArrayAdapter<Distributor> {
    Context mContext;
    int itemLayout;

    public DistributorSpinner(@NonNull Context context, @NonNull List<Distributor> list) {
        super(context, R.layout.item_distributor, list);
        mContext = context;
        itemLayout = R.layout.item_distributor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return run(position, convertView);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return run(position, convertView);
    }

    public View run(int position, @Nullable View view) {
        view = LayoutInflater.from(mContext).inflate(itemLayout, null);

        TextView tvName = view.findViewById(R.id.tv_name);
        tvName.setTextSize(18);
        view.findViewById(R.id.ib_edt).setVisibility(View.GONE);
        view.findViewById(R.id.ib_dlt).setVisibility(View.GONE);

        Distributor item = getItem(position);
        if (item != null) tvName.setText(item.name);

        return view;
    }
}
