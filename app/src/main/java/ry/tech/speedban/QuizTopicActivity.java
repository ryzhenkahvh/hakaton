package ry.tech.speedban;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizTopicActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "QuizAppPrefs";
    private static final String TOPICS_KEY = "Topics";
    private static final String QUESTIONS_KEY = "Questions";

    private ListView listTopics;
    private Button buttonAddTopic;
    private List<String> topics;
    private Map<String, List<Question>> questionsMap;
    private ArrayAdapter<String> adapter;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_topic);

        gson = new Gson();
        listTopics = findViewById(R.id.list_topics);
        buttonAddTopic = findViewById(R.id.button_add_topic);

        topics = new ArrayList<>();
        questionsMap = new HashMap<>();

        loadData(); // Загружаем данные при запуске

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, topics);
        listTopics.setAdapter(adapter);

        listTopics.setOnItemClickListener((parent, view, position, id) -> {
            String selectedTopic = topics.get(position);
            startQuizGame(selectedTopic);
        });

        listTopics.setOnItemLongClickListener((parent, view, position, id) -> {
            String selectedTopic = topics.get(position);
            new AlertDialog.Builder(this)
                    .setTitle("Удалить тему")
                    .setMessage("Вы уверены, что хотите удалить тему " + selectedTopic + "?")
                    .setPositiveButton("Да", (dialog, which) -> {
                        topics.remove(position);
                        questionsMap.remove(selectedTopic);
                        saveData();
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("Нет", null)
                    .show();
            return true;
        });

        buttonAddTopic.setOnClickListener(v -> {
            Intent intent = new Intent(QuizTopicActivity.this, QuestionEditorActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    private void startQuizGame(String topic) {
        Intent intent = new Intent(this, QuizGameActivity.class);
        intent.putExtra("TOPIC", topic);
        intent.putExtra("QUESTIONS", new ArrayList<>(questionsMap.get(topic)));
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String newTopic = data.getStringExtra("TOPIC");
            List<Question> newQuestions = (List<Question>) data.getSerializableExtra("QUESTIONS");
            if (newTopic != null && newQuestions != null) {
                if (topics.contains(newTopic)) {
                    questionsMap.get(newTopic).addAll(newQuestions);
                } else {
                    topics.add(newTopic);
                    questionsMap.put(newTopic, newQuestions);
                }
                saveData();
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String topicsJson = sharedPreferences.getString(TOPICS_KEY, null);
        String questionsJson = sharedPreferences.getString(QUESTIONS_KEY, null);

        if (topicsJson != null && questionsJson != null) {
            Type topicsType = new TypeToken<List<String>>() {}.getType();
            Type questionsType = new TypeToken<Map<String, List<Question>>>() {}.getType();

            topics = gson.fromJson(topicsJson, topicsType);
            questionsMap = gson.fromJson(questionsJson, questionsType);
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String topicsJson = gson.toJson(topics);
        String questionsJson = gson.toJson(questionsMap);

        editor.putString(TOPICS_KEY, topicsJson);
        editor.putString(QUESTIONS_KEY, questionsJson);
        editor.apply();
    }
}