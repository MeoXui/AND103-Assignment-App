package fpoly.huynkph38086.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.jetbrains.annotations.Contract;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import fpoly.huynkph38086.app.models.Response;
import fpoly.huynkph38086.app.models.User;
import fpoly.huynkph38086.app.services.HttpRequest;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class Register extends AppCompatActivity {
    EditText edUN, edPW, edEmail, edName;
    ImageView ivAvt;
    Button btnIn, btnUp;

    HttpRequest request;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edUN = findViewById(R.id.ed_un);
        edPW = findViewById(R.id.ed_pw);
        edEmail = findViewById(R.id.ed_email);
        edName = findViewById(R.id.ed_name);
        ivAvt = findViewById(R.id.iv_avt);
        btnUp = findViewById(R.id.btn_up);
        btnIn = findViewById(R.id.btn_in);

        request = new HttpRequest();

        ivAvt.setOnClickListener(v -> chooseImage());

        btnUp.setOnClickListener(v -> register());
        btnIn.setOnClickListener(v -> finish());
    }

    private boolean validate() {
        return !(edUN.getText().toString().isEmpty() || edPW.getText().toString().isEmpty());
    }

    private void register() {
        if (validate()) {

            RequestBody _username = getRB(edUN);
            RequestBody _password = getRB(edPW);
            RequestBody _email = getRB(edEmail);
            RequestBody _name = getRB(edName);

            MultipartBody.Part part;
            if (file != null) {
                RequestBody requestFile = RequestBody.create(MediaType.get("image/*"), file);
                part = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);
            } else part = null;

            request.api.register(_username, _password, _email, _name, part).enqueue(callback);
            return;
        }
        Toast.makeText(this, "Vui nhập tên tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Contract("_ -> new")
    private RequestBody getRB(@NonNull EditText ed) {
        String content = ed.getText().toString().isEmpty() ? "" : ed.getText().toString();
        return RequestBody.create(MediaType.get("multipart/form-data"), content);
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        getImage.launch(intent);
    }

    ActivityResultLauncher<Intent> getImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), o -> {
        if (o.getResultCode() == Activity.RESULT_OK) {
            assert o.getData() != null;
            Uri imgaePart = o.getData().getData();
            file = createFileFromUri(imgaePart, "avarta");
            Glide.with(this)
                    .load(file)
                    .thumbnail(Glide.with(this).load(R.drawable.ic_broken_image_24x24_rgb888))
                    .centerCrop().centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(ivAvt);
        }
    });

    private File createFileFromUri(Uri path, String name)
    {
        File _file = new File(this.getCacheDir(), name+".png");
        try {
            InputStream in = this.getContentResolver().openInputStream(path);
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



    Callback<Response<User>> callback = new Callback<Response<User>>() {
        @Override
        public void onResponse(@NonNull Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().status == 200) {
                    Toast.makeText(Register.this, response.body().mess, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<Response<User>> call, Throwable t) {
            Log.d("Register: ", t.getMessage());
        }
    };
}