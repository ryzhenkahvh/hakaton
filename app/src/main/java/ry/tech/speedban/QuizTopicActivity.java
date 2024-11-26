package ry.tech.speedban;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class QuizTopicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_topic);

        ImageButton btnBack = findViewById(R.id.btnBack);

        // Устанавливаем слушатель нажатия
        btnBack.setOnClickListener(v -> {
            // Создаем Intent для возврата в MainActivity
            Intent intent = new Intent(QuizTopicActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish(); // Завершаем текущую активность
        });

        Button financeButton = findViewById(R.id.button_finance);
        Button digitalButton = findViewById(R.id.button_digital);
        Button cybersecurityButton = findViewById(R.id.button_cybersecurity);

        financeButton.setOnClickListener(v -> startQuizGame("Финансовая грамотность"));
        digitalButton.setOnClickListener(v -> startQuizGame("Цифровая грамотность"));
        cybersecurityButton.setOnClickListener(v -> startQuizGame("Кибербезопасность"));
    }

    private void startQuizGame(String topic) {
        Intent intent = new Intent(this, QuizGameActivity.class);
        intent.putExtra("TOPIC", topic);
        startActivity(intent);
    }
}
