package fpoly.huynkph38086.app.spinner;

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
import fpoly.huynkph38086.app.models.District;
import fpoly.huynkph38086.app.models.Province;
import fpoly.huynkph38086.app.models.Ward;

public class LocationSpinner<T> extends ArrayAdapter<T> {
    Context mContext;
    int itemLayout;

    public LocationSpinner(@NonNull Context context, @NonNull List<T> list) {
        super(context, R.layout.item_location, list);
        mContext = context;
        itemLayout = R.layout.item_location;
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
        if (view == null)
            view = LayoutInflater.from(mContext).inflate(itemLayout, null);

        TextView tvName = view.findViewById(R.id.tv_name);

        T item = getItem(position);
        Province p = null;
        District d = null;
        Ward w = null;

        if (item.getClass() == Province.class) p = (Province) item;
        if (item.getClass() == District.class) d = (District) item;
        if (item.getClass() == Ward.class) w = (Ward) item;

        if (p != null) tvName.setText(p.name());
        if (d != null) tvName.setText(d.name());
        if (w != null) tvName.setText(w.name());

        return view;
    }
}
