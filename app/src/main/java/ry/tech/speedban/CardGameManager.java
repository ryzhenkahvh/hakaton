package ry.tech.speedban;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGameManager {
    private List<Card> cards;
    private List<Card> definitions;
    private List<Card> terms;
    private int level;
    private String topic;

    public CardGameManager(int level, String topic) {
        this.level = level;
        this.topic = topic;
        initializeCards();
    }

    private void initializeCards() {
        definitions = DataProvider.getDefinitions(topic);
        terms = DataProvider.getTerms(topic);

        Collections.shuffle(definitions);
        Collections.shuffle(terms);

        cards = new ArrayList<>();
        cards.addAll(definitions);
        cards.addAll(terms);
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean checkMatch(Card definition, Card term) {
        return definition.getText().equals(term.getText());
    }
}