package fpoly.huynkph38086.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {
    EditText edUN, edPW, edEmail;
    ImageView ivAVT;
    Button btnIN, btnUP;

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
        ivAVT = findViewById(R.id.iv_avt);
        btnUP = findViewById(R.id.btn_up);
        btnIN = findViewById(R.id.btn_in);

        btnUP.setOnClickListener(v -> {});
        btnIN.setOnClickListener(v -> finish());
    }
}