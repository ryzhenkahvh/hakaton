package ry.tech.speedban;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    public static List<Card> getDefinitions(String topic) {
        List<Card> definitions = new ArrayList<>();
        switch (topic) {
            case "financial":
                definitions.add(new Card("Определение Финансов 1"));
                definitions.add(new Card("Определение Финансов 2"));
                break;
            case "digital":
                definitions.add(new Card("Определение Цифровое 1"));
                definitions.add(new Card("Определение Цифровое 2"));
                break;
            case "cybersecurity":
                definitions.add(new Card("Определение Кибербезопасность 1"));
                definitions.add(new Card("Определение Кибербезопасность 2"));
                break;
        }
        return definitions;
    }

    public static List<Card> getTerms(String topic) {
        List<Card> terms = new ArrayList<>();
        switch (topic) {
            case "financial":
                terms.add(new Card("Термин Финансов 1"));
                terms.add(new Card("Термин Финансов 2"));
                break;
            case "digital":
                terms.add(new Card("Термин Цифровое 1"));
                terms.add(new Card("Термин Цифровое 2"));
                break;
            case "cybersecurity":
                terms.add(new Card("Термин Кибербезопасность 1"));
                terms.add(new Card("Термин Кибербезопасность 2"));
                break;
        }
        return terms;
    }
}
