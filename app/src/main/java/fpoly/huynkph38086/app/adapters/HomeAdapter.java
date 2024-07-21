package fpoly.huynkph38086.app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fpoly.huynkph38086.app.R;
import fpoly.huynkph38086.app.models.Product;

public class HomeAdapter extends ArrayAdapter<Product> {
    Context mContext;
    int itemLayout;

    public HomeAdapter(@NonNull Context context, @NonNull List<Product> list) {
        super(context, R.layout.item_fruit, list);
        mContext = context;
        itemLayout = R.layout.item_fruit;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null)
            view = LayoutInflater.from(mContext).inflate(itemLayout, null);

        ImageView imgAvt = view.findViewById(R.id.iv_avt);
        TextView tvName = view.findViewById(R.id.tv_name),
                tvPrice = view.findViewById(R.id.tv_price);
        ImageButton ibAdd2Cart = view.findViewById(R.id.ib_add2cart);

        Product item = getItem(position);

        if (item != null) {
            tvName.setText(item.name);
            if (item.status == 1) tvName.setTextColor(R.color.green);
            if (item.status == 0) tvName.setTextColor(R.color.red);
            if (item.status == -1) tvName.setTextColor(R.color.light_gray);
            tvPrice.setText(String.valueOf(item.price));
        }

        return view;
    }
}
