package fpoly.huynkph38086.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    EditText edUN, edPW;
    CheckBox chkRE;
    Button btnIN, btnUP;

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

        startActivity(new Intent(this, Register.class));

        edUN = findViewById(R.id.ed_un);
        edPW = findViewById(R.id.ed_pw);
        chkRE = findViewById(R.id.chk_re);
        btnIN = findViewById(R.id.btn_in);
        btnUP = findViewById(R.id.btn_up);

        btnIN.setOnClickListener(v -> startActivity(Main.class));
        btnUP.setOnClickListener(v -> startActivity(Register.class));
    }

    void startActivity(Class<?> activity) {
        startActivity(new Intent(this, activity));
    }
}