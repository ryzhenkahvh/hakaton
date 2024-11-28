package ry.tech.speedban;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    public static List<Card> getDefinitions(Context context, String topic) {
        List<Card> definitions = new ArrayList<>();
        switch (topic) {
            case "financial":
                definitions.add(new Card(context.getString(R.string.financial_definition_1)));
                definitions.add(new Card(context.getString(R.string.financial_definition_2)));
                break;
            case "digital":
                definitions.add(new Card(context.getString(R.string.digital_definition_1)));
                definitions.add(new Card(context.getString(R.string.digital_definition_2)));
                break;
            case "cybersecurity":
                definitions.add(new Card(context.getString(R.string.cybersecurity_definition_1)));
                definitions.add(new Card(context.getString(R.string.cybersecurity_definition_2)));
                break;
        }
        return definitions;
    }

    public static List<Card> getTerms(Context context, String topic) {
        List<Card> terms = new ArrayList<>();
        switch (topic) {
            case "financial":
                terms.add(new Card(context.getString(R.string.financial_term_1)));
                terms.add(new Card(context.getString(R.string.financial_term_2)));
                break;
            case "digital":
                terms.add(new Card(context.getString(R.string.digital_term_1)));
                terms.add(new Card(context.getString(R.string.digital_term_2)));
                break;
            case "cybersecurity":
                terms.add(new Card(context.getString(R.string.cybersecurity_term_1)));
                terms.add(new Card(context.getString(R.string.cybersecurity_term_2)));
                break;
        }
        return terms;
    }
}
