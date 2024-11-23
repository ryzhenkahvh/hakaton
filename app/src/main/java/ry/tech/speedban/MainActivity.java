package ry.tech.speedban;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button lessonsButton = findViewById(R.id.lessonsButton);
        Button quizzesButton = findViewById(R.id.quizzesButton);
        Button profileButton = findViewById(R.id.profileButton);

        lessonsButton.setOnClickListener(view -> switchFragment(new LessonsFragment()));
        quizzesButton.setOnClickListener(view -> switchFragment(new QuizzesFragment()));
        profileButton.setOnClickListener(view -> switchFragment(new ProfileFragment()));

        if (savedInstanceState == null) {
            switchFragment(new LessonsFragment());
        }
    }
    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}