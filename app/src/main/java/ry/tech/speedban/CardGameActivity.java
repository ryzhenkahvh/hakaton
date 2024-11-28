package ry.tech.speedban;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.Button;
import java.util.List;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.util.TypedValue;

public class CardGameActivity extends AppCompatActivity {

    private CardGameManager cardGameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game);

        String selectedTopic = getIntent().getStringExtra("selectedTopic");

        cardGameManager = new CardGameManager(this, 1, selectedTopic);

        GridLayout definitionsGridLayout = findViewById(R.id.definitionsGridLayout);
        GridLayout termsGridLayout = findViewById(R.id.termsGridLayout);

        addCardsToGridLayout(definitionsGridLayout, cardGameManager.getDefinitions(), R.drawable.card_definition_background);
        addCardsToGridLayout(termsGridLayout, cardGameManager.getTerms(), R.drawable.card_term_background);
    }

    private void addCardsToGridLayout(GridLayout gridLayout, List<Card> cards, int backgroundResource) {
        for (Card card : cards) {
            Button cardButton = new Button(this);
            cardButton.setText(card.isFlipped() ? card.getText() : "");
            cardButton.setBackground(getDrawable(backgroundResource));

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 300;
            params.height = 400;
            int marginInDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
            params.setMargins(marginInDp, marginInDp, marginInDp, marginInDp);
            cardButton.setLayoutParams(params);

            cardButton.setOnClickListener(v -> {
                card.flip();
                cardButton.setText(card.isFlipped() ? card.getText() : "");
            });

            gridLayout.addView(cardButton);
        }
    }
}