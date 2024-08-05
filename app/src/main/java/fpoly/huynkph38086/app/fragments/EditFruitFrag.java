package fpoly.huynkph38086.app.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.jetbrains.annotations.Contract;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fpoly.huynkph38086.app.R;
import fpoly.huynkph38086.app.adapters.DistributorSpinner;
import fpoly.huynkph38086.app.models.Distributor;
import fpoly.huynkph38086.app.models.Fruit;
import fpoly.huynkph38086.app.services.Response;
import fpoly.huynkph38086.app.services.HttpRequest;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class EditFruitFrag extends Fragment {
    EditText edName, edQuantity, edPrice, edDes;
    ImageView ivImg;
    CheckBox chk1, chk0, chk_1;
    Spinner spn;
    Button btnSeve;

    Activity activity;
    Context context;
    HttpRequest request;
    DistributorSpinner spinner;
    File file;

    List<File> listImg;
    String status;
    List<Distributor> listDistributors;
    Distributor distributor;
    String id_distributor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_fruit, container, false);

        ivImg = view.findViewById(R.id.iv_img);
        edName = view.findViewById(R.id.ed_name);
        edQuantity = view.findViewById(R.id.ed_quantity);
        edPrice = view.findViewById(R.id.ed_price);
        chk1 = view.findViewById(R.id.chk1);
        chk0 = view.findViewById(R.id.chk0);
        chk_1 = view.findViewById(R.id.chk_1);
        group();
        spn = view.findViewById(R.id.spn);
        edDes = view.findViewById(R.id.ed_des);
        btnSeve = view.findViewById(R.id.btn_save);

        listImg = new ArrayList<>();
        status = "1";
        listDistributors = new ArrayList<>();

        activity = getActivity();
        context = activity;
        request = new HttpRequest();

        refresh();

        ivImg.setOnClickListener(v -> chooseImage());

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                distributor = spinner.getItem(position);
                assert distributor != null;
                id_distributor = distributor._id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn.setSelection(0);

        btnSeve.setOnClickListener(v -> addFruit());

        return view;
    }

    void refresh()
    {
        request.api.getDistributors().enqueue(callbackDistributors);
    }

    private boolean validate() {
        return !(edName.getText().toString().isEmpty()
                || edQuantity.getText().toString().isEmpty()
                || edPrice.getText().toString().isEmpty());
    }

    void addFruit() {
        if (validate()) {
            Map<String, RequestBody> map = new HashMap<>();

            map.put("name", getRB(edName));
            map.put("quantity", getRB(edQuantity));
            map.put("price", getRB(edPrice));
            map.put("status", getRB(status));
            map.put("des", getRB(edDes));
            map.put("id_distributor", getRB(id_distributor));

            ArrayList<MultipartBody.Part> images = new ArrayList<>();
            MultipartBody.Part part;
            for (int i = 0; i < listImg.size(); i++){
                RequestBody requestFile = RequestBody.create(MediaType.get("image/*"), listImg.get(i));
                part = MultipartBody.Part.createFormData("images", listImg.get(i).getName(), requestFile);
                images.add(part);
            }

            request.api.addFruit(map, images).enqueue(callback);
            return;
        }
        Toast.makeText(context, "Vui nhập đầy đủ tên, số lượng và giá sản phẩm", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Contract("_ -> new")
    private RequestBody getRB(@NonNull EditText ed) {
        String content = ed.getText().toString().isEmpty() ? "" : ed.getText().toString();
        return RequestBody.create(MediaType.get("multipart/form-data"), content);
    }

    @NonNull
    @Contract("_ -> new")
    private RequestBody getRB(@NonNull String s) {
        return RequestBody.create(MediaType.get("multipart/form-data"), s);
    }

    void group() {
        chk1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                status = "1";
                chk0.setChecked(false);
                chk_1.setChecked(false);
            } else if (!chk1.isChecked() && !chk0.isChecked() && !chk_1.isChecked())
                chk0.setChecked(true);
        });

        chk0.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                status = "0";
                chk1.setChecked(false);
                chk_1.setChecked(false);
            } else if (!chk1.isChecked() && !chk0.isChecked() && !chk_1.isChecked())
                chk1.setChecked(true);
        });

        chk_1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                status = "-1";
                chk1.setChecked(false);
                chk0.setChecked(false);
            } else if (!chk1.isChecked() && !chk0.isChecked() && !chk_1.isChecked())
                chk1.setChecked(true);
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        getImage.launch(intent);
    }

    ActivityResultLauncher<Intent> getImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), o -> {
        if (o.getResultCode() == Activity.RESULT_OK) {
            assert o.getData() != null;
            if (o.getData().getClipData() != null) {
                int size = o.getData().getClipData().getItemCount();
                for (int i = 0; i < size; i++) {
                    Uri imgaePart = o.getData().getClipData().getItemAt(i).getUri();
                    file = createFileFromUri(imgaePart, "img" + i);
                    listImg.add(file);
                }
                Glide.with(this)
                        .load(listImg.get(0))
                        .thumbnail(Glide.with(this).load(R.drawable.ic_broken_image_24x24_rgb888))
                        .centerCrop().centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(ivImg);
            } else {

            }
        }
    });

    private File createFileFromUri(Uri path, String name)
    {
        File _file = new File(activity.getCacheDir(), name+".png");
        try {
            InputStream in = activity.getContentResolver().openInputStream(path);
            OutputStream out = Files.newOutputStream(_file.toPath());
            byte[] buf = new byte[1024];
            int len;
            assert in != null;
            while ((len=in.read(buf))>0) out.write(buf, 0, len);
            out.close();
            in.close();
            return _file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    Callback<Response<Fruit>> callback = new Callback<Response<Fruit>>() {
        @Override
        public void onResponse(@NonNull Call<Response<Fruit>> call, retrofit2.Response<Response<Fruit>> response) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                Toast.makeText(context, response.body().mess, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(@NonNull Call<Response<Fruit>> call, Throwable t) {
            Log.d("Get Data: ", t.getMessage());
        }
    };

    Callback<Response<ArrayList<Distributor>>> callbackDistributors = new Callback<Response<ArrayList<Distributor>>>() {
        @Override
        public void onResponse(@NonNull Call<Response<ArrayList<Distributor>>> call, retrofit2.Response<Response<ArrayList<Distributor>>> response) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().status == 200) {
                    listDistributors = response.body().data;
                    spinner = new DistributorSpinner(context, listDistributors);
                    spn.setAdapter(spinner);
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