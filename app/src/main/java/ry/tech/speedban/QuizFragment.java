package ry.tech.speedban;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuizFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quiz, container, false);

        // Найдите кнопку и установите обработчик нажатия
        Button startQuizButton = rootView.findViewById(R.id.button_start_quiz);
        startQuizButton.setOnClickListener(v -> startQuizTopicActivity());

        return rootView;
    }

    // Метод для запуска QuizTopicActivity
    private void startQuizTopicActivity() {
        Intent intent = new Intent(getActivity(), QuizTopicActivity.class);
        startActivity(intent);
    }
}
