package fpoly.huynkph38086.app;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fpoly.huynkph38086.app.fragments.CartFrag;
import fpoly.huynkph38086.app.fragments.DistributorFrag;
import fpoly.huynkph38086.app.fragments.HomeFrag;
import fpoly.huynkph38086.app.fragments.InfoFrag;

public class Main extends AppCompatActivity {
    DrawerLayout drawer;
    FrameLayout fr;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        drawer = findViewById(R.id.main);
        nav = findViewById(R.id.nav);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fr, new DistributorFrag()).commit();

        nav.setOnItemSelectedListener(item -> {
            Fragment frag = new Fragment();

            if (item.getItemId() == R.id.nav_Home)
                frag = new HomeFrag();

            else if (item.getItemId() == R.id.nav_Cart)
                frag = new CartFrag();

            else if (item.getItemId() == R.id.nav_Info)
                frag = new InfoFrag();

            else drawer.openDrawer(GravityCompat.START);

            manager.beginTransaction().replace(R.id.fr, frag).commit();

            drawer.closeDrawers();

            return false;
        });
    }
}