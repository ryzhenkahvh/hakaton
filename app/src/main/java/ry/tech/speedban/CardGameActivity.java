package ry.tech.speedban;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.Button;
import java.util.List;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

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

            cardButton.setOnClickListener(v -> flipCard(card, cardButton, backgroundResource));

            gridLayout.addView(cardButton);
        }
    }

    private void flipCard(Card card, Button cardButton, int unflippedBackgroundResource) {
        AnimatorSet flipOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_out);
        AnimatorSet flipIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_in);

        flipOut.setTarget(cardButton);
        flipIn.setTarget(cardButton);

        flipOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                card.flip();
                // Обновляем текст и фон в середине анимации
                int newBackgroundResource = card.isFlipped() ? R.drawable.card_flipped_background : unflippedBackgroundResource;
                cardButton.setBackground(getDrawable(newBackgroundResource));
                cardButton.setText(card.isFlipped() ? card.getText() : "");
                flipIn.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });

        flipOut.start();
    }
}