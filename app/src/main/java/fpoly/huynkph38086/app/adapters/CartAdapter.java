package fpoly.huynkph38086.app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

import fpoly.huynkph38086.app.R;
import fpoly.huynkph38086.app.models.Fruit;

public class CartAdapter extends ArrayAdapter<Fruit> {
    Context mContext;
    int itemLayout;
    ItemHandle<Fruit> mHandle;

    public CartAdapter(@NonNull Context context, @NonNull List<Fruit> list, ItemHandle<Fruit> handle) {
        super(context, R.layout.item_fruit, list);
        mContext = context;
        itemLayout = R.layout.item_fruit;
        mHandle = handle;
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null)
            view = LayoutInflater.from(mContext).inflate(itemLayout, null);

        view.findViewById(R.id.ib_add2cart).setVisibility(View.GONE);

        ImageView ivImg = view.findViewById(R.id.iv_img);
        TextView tvName = view.findViewById(R.id.tv_name),
                tvPrice = view.findViewById(R.id.tv_price);
        ImageButton ibDlt = view.findViewById(R.id.ib_dlt);
        EditText edQuantity = view.findViewById(R.id.ed_quantity);

        Fruit item = getItem(position);

        if (item != null) {
            if (!item.images.isEmpty())
                Glide.with(mContext)
                        .load(item.images.get(0))
                        .thumbnail(Glide.with(mContext).load(R.drawable.ic_broken_image_24x24_rgb888))
                        .into(ivImg);
            tvName.setText(item.name);
            tvName.setTextColor(R.color.black);
            tvPrice.setText(String.valueOf(item.price));
            ibDlt.setOnClickListener(v -> {});
        }

        return view;
    }
}
