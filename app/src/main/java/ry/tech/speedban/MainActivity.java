package ry.tech.speedban;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        if (savedInstanceState == null) {
            switchFragment(new ProfileFragment());
        }
    }

    private boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String itemName = (String) item.getTitle();
        switch (itemName) {
            case "Профиль":
                switchFragment(new ProfileFragment());
                return true;
            case "Игры":
                showGameOptions();
                return true;
            case "Учебник":
                switchFragment(new StudyFragment());
                return true;
        }
        return false;
    }

    private void showGameOptions() {
        // Получаем View для элемента "Игры" в BottomNavigationView
        View anchor = bottomNavigationView.findViewById(R.id.navigation_games);

        PopupMenu popupMenu = new PopupMenu(this, anchor);
        popupMenu.getMenu().add("Определение").setOnMenuItemClickListener(menuItem -> {
            switchFragment(new DetectionFragment());
            return true;
        });
        popupMenu.getMenu().add("Викторина").setOnMenuItemClickListener(menuItem -> {
            switchFragment(new QuizFragment());
            return true;
        });
        popupMenu.getMenu().add("Карточки").setOnMenuItemClickListener(menuItem -> {
            switchFragment(new CardGameFragment());
            return true;
        });
        popupMenu.show();
    }

    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}