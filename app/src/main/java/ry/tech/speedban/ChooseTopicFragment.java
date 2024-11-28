package ry.tech.speedban;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class ChooseTopicFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_theme, container, false);

        Button btnFinancialLiteracy = view.findViewById(R.id.topic_1);
        Button btnDigitalLiteracy = view.findViewById(R.id.topic_2);
        Button btnCyberSecurity = view.findViewById(R.id.topic_3);

        btnFinancialLiteracy.setOnClickListener(v -> startGameWithTopic("financial"));
        btnDigitalLiteracy.setOnClickListener(v -> startGameWithTopic("digital"));
        btnCyberSecurity.setOnClickListener(v -> startGameWithTopic("cybersecurity"));

        return view;
    }

    private void startGameWithTopic(String topic) {
        Intent intent = new Intent(getActivity(), CardGameActivity.class);
        intent.putExtra("selectedTopic", topic);
        startActivity(intent);
    }
}
