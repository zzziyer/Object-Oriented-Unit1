import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polys {

    public static int checkSpace(StringBuffer h) {
        Pattern p1 = Pattern.compile("[-+]\\s*[-+]\\s+\\d");
        Pattern p2 = Pattern.compile("\\d\\s+\\d");
        Pattern p3 = Pattern.compile("\\^\\s*[-+]\\s+\\d");
        Pattern p4 = Pattern.compile("[-+]\\s*[-+]\\s*[-+]");
        Matcher m1 = p1.matcher(h);
        Matcher m2 = p2.matcher(h);
        Matcher m3 = p3.matcher(h);
        Matcher m4 = p4.matcher(h);
        while (m1.find() || m2.find() || m3.find() || m4.find()) {
            return 0;
        }
        return 1;
    }

    public static StringBuffer handle(StringBuffer h) {
        int i;
        for (i = 0; i < h.length(); i++) {
            if (h.charAt(i) != ' ' && h.charAt(i) != '\t') {
                break;
            }
        }
        if (h.charAt(i) != '+' && h.charAt(i) != '-') {
            h.insert(0, '+');
        }
        h.append("+");
        return h;
    }

    public static StringBuffer deleteSpace(StringBuffer h) {
        int i;
        for (i = 0; i < h.length(); i++) {
            if (h.charAt(i) == ' ' || h.charAt(i) == '\t') {
                h.deleteCharAt(i);
                i -= 1;
            }
        }
        return h;
    }

    public static ArrayList<Item> sort(ArrayList<Item> a) {
        HashMap<BigInteger, Item> hashMap = new HashMap<>(16);

        for (Item item :
                a) {
            BigInteger coe = item.getCoe();
            BigInteger index = item.getIndex();
            if (hashMap.containsKey(index)) {
                Item old = hashMap.get(index);
                old.setCoe(coe.add(old.getCoe()));
            } else {
                hashMap.put(index, item);
            }
        }
        return new ArrayList<>(hashMap.values());
    }
}
