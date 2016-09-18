import java.util.HashMap;

/**
 * Created by zouxuan on 9/18/16.
 */
public class ValidWordAbbr {
    private HashMap<String, String> map;

    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for (String s : dictionary) {
            String abbr = getAbbr(s);
            if (abbr.equals(s)) continue;
            if (map.containsKey(abbr)) {
                map.put(abbr, "");
            } else {
                map.put(abbr, s);
            }
        }
    }

    private String getAbbr(String word) {
        int len = word.length();
        if (len <= 2) {
            return word;
        } else {
            int newLen = len - 2;
            return word.charAt(0) + String.valueOf(newLen) + word.charAt(word.length() - 1);
        }
    }

    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        if (!map.containsKey(abbr)) return true;
        else{
            String s=map.get(abbr);
            return s.equals(word);
        }
    }
}
