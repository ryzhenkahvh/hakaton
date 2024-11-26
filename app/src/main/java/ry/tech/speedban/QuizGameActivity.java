package ry.tech.speedban;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizGameActivity extends AppCompatActivity {

    private TextView textViewTopic;
    private TextView textViewQuestion;
    private RadioGroup radioGroupOptions;
    private Button buttonSubmit;
    private TextView textViewFeedback;

    private List<Question> questions;
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);

        textViewTopic = findViewById(R.id.text_view_topic);
        textViewQuestion = findViewById(R.id.text_view_question);
        radioGroupOptions = findViewById(R.id.radio_group_options);
        buttonSubmit = findViewById(R.id.button_submit);
        textViewFeedback = findViewById(R.id.text_view_feedback);

        // Получение данных из Intent
        String topic = getIntent().getStringExtra("TOPIC");
        questions = (List<Question>) getIntent().getSerializableExtra("QUESTIONS");

        if (questions == null || questions.isEmpty()) {
            Toast.makeText(this, "Нет вопросов для этой темы", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        textViewTopic.setText("Тема: " + topic);
        displayQuestion(questions.get(currentQuestionIndex));

        buttonSubmit.setOnClickListener(v -> checkAnswer());
    }

    private void displayQuestion(Question question) {
        textViewQuestion.setText(question.getQuestionText());
        radioGroupOptions.removeAllViews();

        List<String> options = new ArrayList<>(question.getOptions());
        Collections.shuffle(options);

        for (String option : options) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioButton.setLayoutParams(new RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT));
            radioButton.setId(View.generateViewId()); // Уникальный ID для каждого RadioButton

            radioGroupOptions.addView(radioButton);

            // Устанавливаем кликлистенер на сам RadioButton
            radioButton.setOnClickListener(v -> radioGroupOptions.check(radioButton.getId()));
        }
    }

    private void checkAnswer() {
        int selectedId = radioGroupOptions.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedAnswer = selectedRadioButton.getText().toString();
            String correctAnswer = questions.get(currentQuestionIndex).getCorrectAnswer();

            if (selectedAnswer.equals(correctAnswer)) {
                textViewFeedback.setText("Верно!");
            } else {
                textViewFeedback.setText("Неправильно.\nПравильный ответ: " + "'" + correctAnswer + "'.");
            }
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                displayQuestion(questions.get(currentQuestionIndex));
            } else {
                textViewFeedback.append("\nВопросы закончились.");
                buttonSubmit.setEnabled(false);
            }
        } else {
            Toast.makeText(this, "Пожалуйста, выберите ответ.", Toast.LENGTH_SHORT).show();
        }
    }
}