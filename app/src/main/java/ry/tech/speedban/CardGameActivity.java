package ry.tech.speedban;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.Button;
import java.util.List;
import java.util.ArrayList;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class CardGameActivity extends AppCompatActivity {

    private CardGameManager cardGameManager;
    private List<Card> flippedCards = new ArrayList<>();
    private List<Button> flippedButtons = new ArrayList<>();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game);

        String selectedTopic = getIntent().getStringExtra("selectedTopic");
        if (selectedTopic == null) {
            Log.e("CardGameActivity", "No topic selected");
            finish();
            return;
        }

        cardGameManager = new CardGameManager(this, 1, selectedTopic);

        GridLayout definitionsGridLayout = findViewById(R.id.definitionsGridLayout);
        GridLayout termsGridLayout = findViewById(R.id.termsGridLayout);

        addCardsToGridLayout(definitionsGridLayout, cardGameManager.getDefinitions(), R.drawable.card_definition_background, "definition");
        addCardsToGridLayout(termsGridLayout, cardGameManager.getTerms(), R.drawable.card_term_background, "term");
    }

    private void addCardsToGridLayout(GridLayout gridLayout, List<Card> cards, int backgroundResource, String tag) {
        for (Card card : cards) {
            Button cardButton = new Button(this);
            cardButton.setText("");
            cardButton.setBackground(getDrawable(backgroundResource));
            cardButton.setTag(tag);  // Устанавливаем тег для кнопки

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 300;
            params.height = 400;
            int marginInDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
            params.setMargins(marginInDp, marginInDp, marginInDp, marginInDp);
            cardButton.setLayoutParams(params);

            cardButton.setOnClickListener(v -> handleCardFlip(card, cardButton, backgroundResource));

            gridLayout.addView(cardButton);
        }
    }

    private void handleCardFlip(Card card, Button cardButton, int initialBackgroundResource) {
        if (flippedCards.size() >= 2) {
            return;
        }

        flipCard(card, cardButton);

        flippedCards.add(card);
        flippedButtons.add(cardButton);

        if (flippedCards.size() == 2) {
            handler.postDelayed(() -> {
                if (flippedCards.size() == 2) {
                    Card firstCard = flippedCards.get(0);
                    Card secondCard = flippedCards.get(1);

                    if (firstCard != null && secondCard != null && isMatchingPair(firstCard, secondCard)) {
                        setCorrectBackground(flippedButtons.get(0));
                        setCorrectBackground(flippedButtons.get(1));
                    } else {
                        resetCard(firstCard, flippedButtons.get(0), (String) flippedButtons.get(0).getTag());
                        resetCard(secondCard, flippedButtons.get(1), (String) flippedButtons.get(1).getTag());
                    }
                }
                flippedCards.clear();
                flippedButtons.clear();
            }, 1300);
        }
    }
    private boolean isMatchingPair(Card firstCard, Card secondCard) {
        return cardGameManager.checkMatch(firstCard, secondCard);
    }

    private void flipCard(Card card, Button cardButton) {
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
                cardButton.setBackground(getDrawable(R.drawable.card_flipped_background));
                cardButton.setText(card.getText());
                flipIn.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });

        flipOut.start();
    }

    private void resetCard(Card card, Button cardButton, String tag) {
        AnimatorSet flipOut = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_out);
        AnimatorSet flipIn = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_in);

        flipOut.setTarget(cardButton);
        flipIn.setTarget(cardButton);

        int backgroundResource = tag.equals("definition") ? R.drawable.card_definition_background : R.drawable.card_term_background;

        flipOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                card.flip();
                cardButton.setBackground(getDrawable(backgroundResource));
                cardButton.setText(""); // Очищаем текст после неверного ответа
                flipIn.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });

        flipOut.start();
    }

    private void setCorrectBackground(Button button) {
        button.setBackground(getDrawable(R.drawable.card_correct_background));
    }
}