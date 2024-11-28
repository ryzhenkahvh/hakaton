package ry.tech.speedban;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class QuestionEditorActivity extends AppCompatActivity {

    private EditText editTopic;
    private EditText editQuestion;
    private EditText editCorrectWord;
    private LinearLayout layoutOptions;
    private TextView textMessageEditor;
    private Button buttonAddOption;
    private List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_editor);

        editTopic = findViewById(R.id.edit_topic);
        editQuestion = findViewById(R.id.edit_question);
        editCorrectWord = findViewById(R.id.edit_correct_word);
        layoutOptions = findViewById(R.id.layout_options);
        textMessageEditor = findViewById(R.id.text_message_editor);
        buttonAddOption = findViewById(R.id.button_add_option);
        Button buttonSaveQuestion = findViewById(R.id.button_save_question);
        Button buttonFinish = findViewById(R.id.button_finish);

        questions = new ArrayList<>();

        buttonAddOption.setOnClickListener(v -> addOptionField());
        buttonSaveQuestion.setOnClickListener(v -> saveQuestion());
        buttonFinish.setOnClickListener(v -> finishEditing());
    }

    private void addOptionField() {
        EditText newOption = new EditText(this);
        newOption.setHint("Введите вариант ответа");
        layoutOptions.addView(newOption); // Добавляем новое поле для ввода варианта
    }

    private void saveQuestion() {
        String questionText = editQuestion.getText().toString().trim();
        String correctWord = editCorrectWord.getText().toString().trim();
        List<String> options = new ArrayList<>();

        for (int i = 0; i < layoutOptions.getChildCount(); i++) {
            View view = layoutOptions.getChildAt(i);
            if (view instanceof EditText) {
                String optionText = ((EditText) view).getText().toString().trim();
                if (!optionText.isEmpty() && !optionText.equals(correctWord)) {
                    options.add(optionText);
                }
            }
        }

        if (!questionText.isEmpty() && !correctWord.isEmpty() && options.size() > 0) {
            options.add(correctWord); // Добавляем правильный ответ в список вариантов
            questions.add(new Question(questionText, correctWord, options));
            textMessageEditor.setText("Вопрос добавлен!");

            // Очищаем поля для следующего вопроса
            editQuestion.setText("");
            editCorrectWord.setText("");
            layoutOptions.removeAllViews(); // Удаляем все поля для ввода опций

            // Повторно добавляем кнопку для добавления вариантов
            layoutOptions.addView(buttonAddOption);
        } else {
            textMessageEditor.setText("Пожалуйста, заполните все поля и добавьте минимум один вариант.");
        }
    }

    private void finishEditing() {
        String topic = editTopic.getText().toString().trim();
        if (!topic.isEmpty() && !questions.isEmpty()) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("TOPIC", topic);
            resultIntent.putExtra("QUESTIONS", new ArrayList<>(questions)); // Передаем все вопросы
            setResult(RESULT_OK, resultIntent);
            finish();
        } else {
            textMessageEditor.setText("Пожалуйста, добавьте тему и хотя бы один вопрос.");
        }
    }
}