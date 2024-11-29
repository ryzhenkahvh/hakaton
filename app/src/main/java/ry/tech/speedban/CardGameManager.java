package ry.tech.speedban;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGameManager {
    private List<Card> allCards; // Общий список для всех карточек

    public CardGameManager(Context context, int level, String topic) {
        initializeCards(context, topic, level);
    }

    private void initializeCards(Context context, String topic, int level) {
        List<Card> definitions = DataProvider.getDefinitions(context, topic);
        List<Card> terms = DataProvider.getTerms(context, topic);

        // Обрезаем списки до текущего уровня
        definitions = definitions.subList(0, Math.min(level, definitions.size()));
        terms = terms.subList(0, Math.min(level, terms.size()));

        // Создаем общий список карточек
        allCards = new ArrayList<>();
        allCards.addAll(definitions);
        allCards.addAll(terms);

        // Перемешиваем общий список карточек
        Collections.shuffle(allCards);
    }

    public List<Card> getAllCards() {
        return allCards;
    }

    public boolean checkMatch(Card firstCard, Card secondCard) {
        return firstCard.getId() == secondCard.getId();
    }
}
