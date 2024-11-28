package ry.tech.speedban;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.Button;
import java.util.List;

public class CardGameActivity extends AppCompatActivity {

    private CardGameManager cardGameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game);

        String selectedTopic = getIntent().getStringExtra("selectedTopic");

        cardGameManager = new CardGameManager(1, selectedTopic);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        List<Card> cards = cardGameManager.getCards();

        for (Card card : cards) {
            Button cardButton = new Button(this);
            cardButton.setText(card.isFlipped() ? card.getText() : "");

            cardButton.setOnClickListener(v -> {
                card.flip();
                cardButton.setText(card.isFlipped() ? card.getText() : "");
            });

            gridLayout.addView(cardButton);
        }
    }
}