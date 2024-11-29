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

public class DetectionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detection, container, false);

        Button button1 = view.findViewById(R.id.topic_1);
        Button button2 = view.findViewById(R.id.topic_2);
        Button button3 = view.findViewById(R.id.topic_3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic1 = getString(R.string.FLiteracy);
                openDetectionsActivity(topic1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic2 = getString(R.string.DLiteracy);
                openDetectionsActivity(topic2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic3 = getString(R.string.Cybersecurity);
                openDetectionsActivity(topic3);
            }
        });

        return view;
    }

    private void openDetectionsActivity(String theme) {
        Intent intent = new Intent(getActivity(), DetectionActivity.class);
        intent.putExtra("theme", theme);
        startActivity(intent);
    }
}
