package ry.tech.speedban;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    public static List<Card> getDefinitions(Context context, String topic) {
        List<Card> definitions = new ArrayList<>();
        switch (topic) {
            case "financial":
                definitions.add(new Card(context.getString(R.string.definition_1), 1));
                definitions.add(new Card(context.getString(R.string.definition_2), 2));
                break;
            // Другие темы
        }
        return definitions;
    }

    public static List<Card> getTerms(Context context, String topic) {
        List<Card> terms = new ArrayList<>();
        switch (topic) {
            case "financial":
                terms.add(new Card(context.getString(R.string.term_1), 1));
                terms.add(new Card(context.getString(R.string.term_2), 2));
                break;
            // Другие темы
        }
        return terms;
    }
}
