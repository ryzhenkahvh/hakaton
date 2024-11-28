package ry.tech.speedban;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.util.Log;
import androidx.core.content.ContextCompat;

public class CardGameActivity extends AppCompatActivity {

    private List<Card> flippedCards = new ArrayList<>();
    private List<Button> flippedButtons = new ArrayList<>();
    private Handler handler = new Handler();
    private GridLayout definitionsGridLayout;
    private GridLayout termsGridLayout;
    private int currentLevel = 2; // Начальный уровень

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

        definitionsGridLayout = findViewById(R.id.definitionsGridLayout);
        termsGridLayout = findViewById(R.id.termsGridLayout);

        List<Card> definitions = DataProvider.getDefinitions(this, selectedTopic);
        List<Card> terms = DataProvider.getTerms(this, selectedTopic);

        setupLevel(definitions, terms);
    }

    private void setupLevel(List<Card> definitions, List<Card> terms) {
        definitionsGridLayout.removeAllViews();
        termsGridLayout.removeAllViews();

        if (definitions.size() < currentLevel || terms.size() < currentLevel) {
            showMainMenuButton();
            Toast.makeText(this, "Игра окончена! Все уровни пройдены.", Toast.LENGTH_LONG).show();
            return;
        }

        Toast.makeText(this, "Уровень " + (currentLevel - 1), Toast.LENGTH_SHORT).show();

        // Добавляем карты на уровень
        addCardsToGridLayout(definitionsGridLayout, definitions.subList(0, Math.min(currentLevel, definitions.size())), R.drawable.card_definition_background, "definition");
        addCardsToGridLayout(termsGridLayout, terms.subList(0, Math.min(currentLevel, terms.size())), R.drawable.card_term_background, "term");
    }

    private void addCardsToGridLayout(GridLayout gridLayout, List<Card> cards, int backgroundResource, String tag) {
        for (Card card : cards) {
            Button cardButton = new Button(this);
            cardButton.setText("");
            cardButton.setBackground(getDrawable(backgroundResource));
            cardButton.setTag(tag);

            cardButton.setTextColor(ContextCompat.getColor(this, R.color.card_text_color)); // Изменение цвета текста

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

                    if (firstCard != null && secondCard != null && firstCard.getId() == secondCard.getId()) {
                        setCorrectBackground(flippedButtons.get(0));
                        setCorrectBackground(flippedButtons.get(1));

                        // Проверка на окончание уровня
                        if (allCardsMatched()) {
                            currentLevel += 1; // Увеличиваем количество карт
                            setupLevel(DataProvider.getDefinitions(this, getIntent().getStringExtra("selectedTopic")),
                                    DataProvider.getTerms(this, getIntent().getStringExtra("selectedTopic")));
                        }
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

    private boolean allCardsMatched() {
        for (int i = 0; i < definitionsGridLayout.getChildCount(); i++) {
            Button button = (Button) definitionsGridLayout.getChildAt(i);
            if (!button.getBackground().getConstantState().equals(getDrawable(R.drawable.card_correct_background).getConstantState())) {
                return false;
            }
        }

        for (int i = 0; i < termsGridLayout.getChildCount(); i++) {
            Button button = (Button) termsGridLayout.getChildAt(i);
            if (!button.getBackground().getConstantState().equals(getDrawable(R.drawable.card_correct_background).getConstantState())) {
                return false;
            }
        }

        return true;
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

    private void showMainMenuButton() {
        // Показываем кнопку, когда все уровни завершены
        Button mainMenuButton = findViewById(R.id.mainMenuButton);
        mainMenuButton.setVisibility(View.VISIBLE);
        mainMenuButton.setOnClickListener(v -> finish());
    }
}