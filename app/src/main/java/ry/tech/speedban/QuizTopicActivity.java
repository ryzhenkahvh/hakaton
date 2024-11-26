package ry.tech.speedban;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class QuizTopicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_topic);

        Button financeButton = findViewById(R.id.button_finance);
        Button digitalButton = findViewById(R.id.button_digital);
        Button cybersecurityButton = findViewById(R.id.button_cybersecurity);

        financeButton.setOnClickListener(v -> startQuiz("Финансовая грамотность"));
        digitalButton.setOnClickListener(v -> startQuiz("Цифровая грамотность"));
        cybersecurityButton.setOnClickListener(v -> startQuiz("Кибербезопасность"));
    }

    private void startQuiz(String topic) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("TOPIC", topic);
        startActivity(intent);
    }
}
