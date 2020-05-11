import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO 26爆栈 32内部嵌套
public class Polys {
    public static boolean buding(String k) {
        Pattern p = Pattern.compile("[-+]?\\d+");
        Matcher m = p.matcher(k);
        if (m.find()) {
            if (m.group().length() == k.length()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    //检测cos()格式是否正确
    public static boolean cosCheck(String k) {
        if (k.length() > 5 && k.charAt(1) == 'o'
                && k.charAt(2) == 's'
                && k.charAt(3) == '(') {
            int[] t = Polys.brcket(k);
            if (t[3] == k.length() - 1) {
                return true;
            } else {
                Polys.wrong();
                return false;
            }
        } else {
            Polys.wrong();
            return false;
        }
    }

    //检测sin()格式是否正确
    public static boolean sinCheck(String k) {
        if (k.length() > 5 && k.charAt(1) == 'i' && k.charAt(2) == 'n'
                && k.charAt(3) == '(') {
            int[] t = Polys.brcket(k);
            if (t[3] == k.length() - 1) {
                return true;
            } else {
                Polys.wrong();
                return false;
            }
        } else {
            Polys.wrong();
            return false;
        }
    }

    //检测括号类格式是否正确
    public static boolean braCheck(String k) {
        int[] f = Polys.brcket(k);
        if (f[0] == k.length() - 1) {
            return true;
        } else {
            Polys.wrong();
            return false;
        }
    }

    //检测常数格式是否正确
    public static boolean costantCheck(String k) {
        //System.out.println("kwwwwww"+k);
        Pattern p = Pattern.compile("[-+]?\\d+");
        Matcher m = p.matcher(k);
        if (m.find()) {
            if (m.group().length() == k.length()) {
                return true;
            } else {
                Polys.wrong();
                return false;
            }
        } else {
            Polys.wrong();
            return false;
        }
    }

    //检测幂函数格式是否正确
    public static boolean powCheck(String k) {
        Pattern poly = Pattern.compile("(x\\^[-+]?\\d+)|" +
                "(cos\\(.*\\)\\^[-+]?\\d+)|" +
                "(sin\\(.*\\)\\^[-+]?\\d+)");
        Matcher p = poly.matcher(k);
        if (p.find()) {
            String tiao = p.group();
            if (tiao.length() == k.length()) {
                return true;
            } else {
                Polys.wrong();
                return false;
            }
        } else {
            Polys.wrong();
            return false;
        }

    }

    //输出一个01数组，如果表达式在此下标前面括号已经完全匹配则为1，否则为0
    public static int[] createSplitPossible(String h) {
        int[] a = Polys.brcket(h);
        int i;
        int[] re = new int[100];
        int ink = 1;
        int max = 0;
        for (i = 0; i < h.length(); i++) {
            if (h.charAt(i) == '(') {
                ink = 0;
                re[i] = ink;
                if (a[i] >= max) {
                    max = a[i];
                }
                continue;
            }
            if (i > max) {
                ink = 1;
            }
            re[i] = ink;
        }
        return re;
    }

    //emm
    public static void wrong() {
        System.out.println("WRONG FORMAT!");
        System.exit(0);
    }

    //检测若有三个符号，其后是不是数字，不是数字则错
    public static boolean checkTribleAdd(StringBuffer h) {
        Pattern p1 = Pattern.compile("[-+][-+][-+]");
        Pattern p2 = Pattern.compile("[-+][-+][-+]\\d");
        Pattern p3 = Pattern.compile("[-+][-+][-+][-+]\\d");
        Matcher m1 = p1.matcher(h);
        Matcher m2 = p2.matcher(h);
        Matcher m3 = p3.matcher(h);
        while (m3.find()) {
            return false;
        }
        int num1 = 0;
        int num2 = 0;
        while (m1.find()) {
            num1++;
        }
        while (m2.find()) {
            num2++;
        }
        if (num1 == num2) {
            return true;
        } else {
            return false;
        }
    }

    //括号匹配检测，并返回一个数组，数组中有左括号在的下标的位置会存储与其配对的右括号下标
    //若括号不匹配，a[0]=-100
    public static int[] brcket(String eq) {
        int[] a = new int[100];
        int[] temp = new int[100];
        char[] stack = new char[100];
        int last = 0;
        int i;
        for (i = 0; i < eq.length(); i++) {
            if (eq.charAt(i) == '(') {
                stack[last] = '(';
                temp[last] = i;
                last++;
                continue;
            }
            if (eq.charAt(i) == ')') {
                if (last == 0) {
                    a[0] = -100;
                    return a;
                } else {
                    if (stack[last - 1] == '(') {
                        a[temp[last - 1]] = i;
                        last -= 1;
                        stack[last] = ' ';
                        temp[last] = 0;
                    } else {
                        a[0] = -100;
                        return a;
                    }
                }
            }

        }
        if (last != 0) {
            a[0] = -100;
        }
        return a;
    }

    //按照乘号分割
    public static ArrayList<String> splitByMulSign(String h) {
        ArrayList<String> re = new ArrayList<>();
        int t = 3;
        int flag = 1;
        StringBuffer temp = new StringBuffer(h);
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
        if (flag == -1) {
            if (Polys.buding(temp.toString())) {
                temp.insert(0, '-');
            } else {
                re.add("-1");
            }
        }

        String hh = temp.toString();
        int[] can = Polys.createSplitPossible(hh);
        ArrayList<Integer> footer = new ArrayList<>();
        footer.add(0);
        for (int i = 1; i < hh.length(); i++) {
            if (hh.charAt(i) == '*' && can[i] == 1) {
                footer.add(i);
            }
        }
        footer.add(hh.length());
        for (int i = 1; i < footer.size(); i++) {
            re.add(hh.substring(footer.get(i - 1), footer.get(i)));
        }
        for (int i = 1; i < re.size(); i++) {
            if (re.get(i).charAt(0) == '*') {
                re.set(i, re.get(i).substring(1));
            }
        }
        return re;
    }

    //检测pow
    public static int[] powFinder(String h) {
        int[] can = Polys.createSplitPossible(h);
        int[] re = new int[3];
        int count = 0;
        int dst = -1;
        int i;
        for (i = 1; i < h.length(); i++) {
            if (h.charAt(i) == '^' && can[i] == 1) {
                count++;
                dst = i;
            }
        }
        re[0] = count;
        re[1] = dst;
        return re;
    }


    //按照加号分割
    public static ArrayList<String> splitByPlusSign(StringBuffer h) {
        int[] can = Polys.createSplitPossible(h.toString());
        ArrayList<String> a = new ArrayList<>();
        ArrayList<Integer> footer = new ArrayList<>();
        footer.add(0);
        int i;
        for (i = 1; i < h.length(); i++) {
            if ((h.charAt(i) == '+' || h.charAt(i) == '-')
                    && h.charAt(i - 1) != '^' && h.charAt(i - 1) != '*'
                    && h.charAt(i - 1) != '+' && h.charAt(i - 1) != '-'
                    && can[i] == 1) {
                footer.add(i);
            }
        }
        footer.add(h.length());
        for (i = 1; i < footer.size(); i++) {
            a.add(h.substring(footer.get(i - 1), footer.get(i)));
        }
        return a;
    }

    //检测由于全是空格或是空输入引起的错误
    public static int checkNoneOrBlank(String a) {
        if (a.length() == 0 || a.trim().length() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int checkSpace(StringBuffer h) {
        //几种由于空字符引起的错误格式
        Pattern p1 = Pattern.compile("[-+]\\s*[-+]\\s*[-+]\\s+\\d");//+++ 5
        Pattern p2 = Pattern.compile("\\d\\s+\\d");// 9 4
        Pattern p3 = Pattern.compile("\\^\\s*[-+]\\s+\\d");//^- 6
        Pattern p4 = Pattern.compile("c\\s+o\\s*s");//c os
        Pattern p5 = Pattern.compile("c\\s*o\\s+s");//co s
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

    //删除所有空字符
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
}
