package ry.tech.speedban;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuizGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);

        String topic = getIntent().getStringExtra("TOPIC");
        TextView topicTextView = findViewById(R.id.text_topic);
        topicTextView.setText("Тема: " + topic);
        // Логика начала игры в зависимости от выбранной темы
    }
}