import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polys {

    public static ArrayList<Item> createItem(ArrayList<String> a) {
        ArrayList<Item> x = new ArrayList<>();
        ArrayList<String> create = new ArrayList<>();
        Pattern poly = Pattern.compile("(\\*x\\^[-+]?\\d+)|" +
                "(\\*cos\\(x\\)\\^[-+]?\\d+)|" +
                "(\\*sin\\(x\\)\\^[-+]?\\d+)|" +
                "(\\*cos\\(x\\))|" +
                "(\\*sin\\(x\\))|" +
                "(\\*x)|" +
                "(\\*[-+]?\\d+)");
        Matcher po;
        for (String s :
                a) {
            //对于每一项做预处理，先检查若有3个[+-]后面的是不是数字，
            //合并前面的正负号以及添加*
            //System.out.println("ssswww"+s);
            int t = 3;
            int flag = 1;
            StringBuffer temp = new StringBuffer(s);
            while (t-- > 0) {
                if (temp.length() == 0) {
                    break;
                }
                if (temp.charAt(0) == '+' || temp.charAt(0) == '-') {
                    if (temp.charAt(0) == '-') {
                        flag *= -1;
                    }
                    temp.deleteCharAt(0);
                } else {
                    break;
                }
            }
            if (t == -1 && (temp.length() == 0 ||
                    !Character.isDigit(temp.charAt(0)))) {
                x.clear();
                break;
            }
            if (flag == -1) {
                create.add("*-1");
            }
            temp.insert(0, '*');
            s = temp.toString();
            //System.out.println(s);
            int num = 0;
            po = poly.matcher(s);
            while (po.find()) {
                String group = po.group();
                create.add(group);
                num += group.length();
            }
            //System.out.println("num="+num);
            //System.out.println("s.length="+s.length());
            if (num == s.length()) {
                x.add(new Item(create));
                //System.out.println("www");
            } else {
                x.clear();
                break;
            }
            create.clear();
        }
        return x;
    }

    public static ArrayList<String> splitByPlusSign(StringBuffer h) {
        ArrayList<String> a = new ArrayList<>();
        ArrayList<Integer> footer = new ArrayList<>();
        footer.add(0);
        int i;
        for (i = 1; i < h.length(); i++) {
            if ((h.charAt(i) == '+' || h.charAt(i) == '-')
                    && h.charAt(i - 1) != '^' && h.charAt(i - 1) != '*'
                    && h.charAt(i - 1) != '+' && h.charAt(i - 1) != '-') {
                footer.add(i);
            }
        }
        footer.add(h.length());
        for (i = 1; i < footer.size(); i++) {
            a.add(h.substring(footer.get(i - 1), footer.get(i)));
        }
        return a;
    }

    public static int checkNoneOrBlank(String a) {
        if (a.length() == 0 || a.trim().length() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int checkSpace(StringBuffer h) {
        //几种由于空格引起的错误格式
        Pattern p1 = Pattern.compile("[-+]\\s*[-+]\\s*[-+]\\s+\\d");//+++ 5
        Pattern p2 = Pattern.compile("\\d\\s+\\d");// 9 4
        Pattern p3 = Pattern.compile("\\^\\s*[-+]\\s+\\d");//^- 6
        Pattern p4 = Pattern.compile("c\\s+os");//c os
        Pattern p5 = Pattern.compile("co\\s+s");//co s
        Pattern p6 = Pattern.compile("s\\s+i\\s*n");//s in
        Pattern p7 = Pattern.compile("s\\s*i\\s+n");//si n
        Pattern p8 = Pattern.compile("\\*\\s*[-+]\\s+\\d");//*- 8
        Matcher m1 = p1.matcher(h);
        Matcher m2 = p2.matcher(h);
        Matcher m3 = p3.matcher(h);
        Matcher m4 = p4.matcher(h);
        Matcher m5 = p5.matcher(h);
        Matcher m6 = p6.matcher(h);
        Matcher m7 = p7.matcher(h);
        Matcher m8 = p8.matcher(h);
        while (m1.find() || m2.find() || m3.find() ||
                m4.find() || m5.find() || m6.find()) {
            return 0;
        }
        while (m7.find() || m8.find()) {
            return 0;
        }
        return 1;
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
        HashMap<Key, Item> hashMap = new HashMap<>(16);

        for (Item item :
                a) {
            BigInteger coe = item.getCoe();
            BigInteger index = item.getIndex();
            BigInteger cos = item.getCos();
            BigInteger sin = item.getSin();
            Key key = new Key(index, cos, sin);
            if (hashMap.containsKey(key)) {
                Item old = hashMap.get(key);
                old.setCoe(coe.add(old.getCoe()));
            } else {
                hashMap.put(key, item);
            }
        }
        return new ArrayList<>(hashMap.values());
    }
}
