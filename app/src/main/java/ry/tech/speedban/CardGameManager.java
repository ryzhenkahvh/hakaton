package ry.tech.speedban;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGameManager {
    private List<Card> definitions;
    private List<Card> terms;

    public CardGameManager(Context context, int level, String topic) {
        initializeCards(context, topic);
    }

    private void initializeCards(Context context, String topic) {
        definitions = DataProvider.getDefinitions(context, topic);
        terms = DataProvider.getTerms(context, topic);
        Collections.shuffle(definitions);
        Collections.shuffle(terms);
    }

    public List<Card> getDefinitions() {
        return definitions;
    }

    public List<Card> getTerms() {
        return terms;
    }

    public boolean checkMatch(Card definition, Card term) {
        return definition.getText().equals(term.getText());
    }
}