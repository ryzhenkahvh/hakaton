package ry.tech.speedban;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button detectionButton = findViewById(R.id.detectionButton);
        Button quizzesButton = findViewById(R.id.quizzesButton);
        Button cardButton = findViewById(R.id.cardButton);

        detectionButton.setOnClickListener(view -> switchFragment(new DetectionFragment()));
        quizzesButton.setOnClickListener(view -> switchFragment(new QuizzesFragment()));
        cardButton.setOnClickListener(view -> switchFragment(new CardsFragment()));

        if (savedInstanceState == null) {
            switchFragment(new DetectionFragment());
        }
    }
    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}