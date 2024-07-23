package fpoly.huynkph38086.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fpoly.huynkph38086.app.R;
import fpoly.huynkph38086.app.models.Distributor;

public class DistributorAdapter extends ArrayAdapter<Distributor> {
    Context mContext;
    int itemLayout;

    public DistributorAdapter(@NonNull Context context, @NonNull List<Distributor> list) {
        super(context, R.layout.item_distributor, list);
        mContext = context;
        itemLayout = R.layout.item_distributor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null)
            view = LayoutInflater.from(mContext).inflate(itemLayout, null);

        TextView tvName = view.findViewById(R.id.tv_name);
        ImageButton ibEdt = view.findViewById(R.id.ib_edt),
                ibDlt = view.findViewById(R.id.ib_dlt);

        Distributor item = getItem(position);

        if (item != null) tvName.setText(item.name);

        return view;
    }
}
