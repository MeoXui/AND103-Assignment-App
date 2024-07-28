package fpoly.huynkph38086.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fpoly.huynkph38086.app.models.Response;
import fpoly.huynkph38086.app.models.User;
import fpoly.huynkph38086.app.services.HttpRequest;
import retrofit2.Call;
import retrofit2.Callback;

public class Login extends AppCompatActivity {
    EditText edUN, edPW;
    CheckBox chkRE;
    Button btnIN, btnUP;

    HttpRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //startActivity(new Intent(this, Register.class));

        edUN = findViewById(R.id.ed_un);
        edPW = findViewById(R.id.ed_pw);
        chkRE = findViewById(R.id.chk_re);
        btnIN = findViewById(R.id.btn_in);
        btnUP = findViewById(R.id.btn_up);

        request = new HttpRequest();

        btnIN.setOnClickListener(v -> login());
        btnUP.setOnClickListener(v -> startActivity(Register.class));
    }

    private boolean validate() {
        return !(edUN.getText().toString().isEmpty() || edPW.getText().toString().isEmpty());
    }

    private void login() {
        if (validate()) {
            User user = new User(edUN.getText().toString(), edPW.getText().toString());
            request.api.login(user).enqueue(callback);
            return;
        }
        Toast.makeText(this, "Vui lòng nhập đầy đủ!", Toast.LENGTH_SHORT).show();
    }

    void startActivity(Class<?> activity) {
        startActivity(new Intent(this, activity));
    }

    Callback<Response<User>> callback = new Callback<Response<User>>() {
        @Override
        public void onResponse(@NonNull Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                if (response.body().status == 200) {
                    SharedPreferences sp = getSharedPreferences("INFO", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", response.body().token);
                    editor.putString("refreshToken", response.body().refreshToken);
                    editor.putString("id", response.body().data._id);
                    editor.apply();
                    startActivity(Main.class);
                }
                Toast.makeText(Login.this, response.body().mess, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<Response<User>> call, Throwable t) {
            Log.d("Login: ", t.getMessage());
        }
    };
}