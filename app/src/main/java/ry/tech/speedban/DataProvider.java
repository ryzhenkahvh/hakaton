package ry.tech.speedban;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    public static List<Card> getDefinitions(Context context, String topic) {
        List<Card> definitions = new ArrayList<>();
        int i = 1;
        while (true) {
            int resId = context.getResources().getIdentifier(topic + "_definition_" + i, "string", context.getPackageName());
            if (resId == 0) {
                break;
            }
            definitions.add(new Card(context.getString(resId), i));
            i++;
        }
        return definitions;
    }

    public static List<Card> getTerms(Context context, String topic) {
        List<Card> terms = new ArrayList<>();
        int i = 1;
        while (true) {
            int resId = context.getResources().getIdentifier(topic + "_term_" + i, "string", context.getPackageName());
            if (resId == 0) {
                break;
            }
            terms.add(new Card(context.getString(resId), i));
            i++;
        }
        return terms;
    }
}
