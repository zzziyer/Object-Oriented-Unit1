import java.util.ArrayList;

public class Build {

    public static Base buildtree(String h) {
        ArrayList<String> a = Polys.splitByPlusSign(new StringBuffer(h));
        ArrayList<Base> plusSign = new ArrayList<>();
        for (String s :
                a) {
            ArrayList<String> strings = Polys.splitByMulSign(s);
            if (strings.size() == 0) {
                Polys.wrong();
            }
            ArrayList<Base> mulSign = recurMulFac(strings);
            while (mulSign.size() > 1) {
                mulSign.add(new Mul(mulSign.get(0).getSelfString(),
                        mulSign.get(1).getSelfString(),
                        mulSign.get(0), mulSign.get(1),
                        "(" + mulSign.get(0).getSelfString() + "*"
                                + mulSign.get(1).getSelfString() + ")"));
                mulSign.remove(0);
                mulSign.remove(0);
            }
            plusSign.add(mulSign.get(0));
        }
        while (plusSign.size() > 1) {
            plusSign.add(new Add(plusSign.get(0).getSelfString(),
                    plusSign.get(1).getSelfString(),
                    plusSign.get(0), plusSign.get(1),
                    "(" + plusSign.get(0).getSelfString() + "+"
                            + plusSign.get(1).getSelfString() + ")"));
            plusSign.remove(0);
            plusSign.remove(0);
        }
        return plusSign.get(0);
    }

    //递归填补称号因子
    public static ArrayList<Base> recurMulFac(ArrayList<String> strings) {
        ArrayList<Base> re = new ArrayList<>();
        for (String k :
                strings) {
            int[] num = Polys.powFinder(k);
            if (num[0] == 1) {
                if (Polys.powCheck(k)) {
                    int i = num[1];
                    re.add(new Pow(k.substring(0, i), k.substring(i + 1),
                            Build.buildtree(k.substring(0, i)),
                            new Contant(k.substring(i + 1)), k));
                }
            } else {
                if (k.length() == 0) {
                    Polys.wrong();
                }
                char ch = k.charAt(0);
                //是sin、cos、x、\\d、()
                if (ch == 'c') { //cos()
                    if (Polys.cosCheck(k)) {
                        re.add(new Cos(k.substring(4, k.length() - 1),
                                Build.buildtree(k.substring(4, k.length() - 1))
                                , k));
                    }
                } else if (ch == 's') {
                    if (Polys.sinCheck(k)) {
                        re.add(new Sin(k.substring(4, k.length() - 1),
                                Build.buildtree(k.substring(4, k.length() - 1)),
                                k));
                    }
                } else if (ch == 'x') {
                    if (k.length() == 1) {
                        re.add(new X(k));
                    } else {
                        Polys.wrong();
                    }
                } else if (ch == '(') {
                    if (Polys.braCheck(k)) {
                        re.add(new Brcket(k.substring(1, k.length() - 1),
                                Build.buildtree(k.substring(1, k.length() - 1)),
                                k));
                    }
                } else if (ch == '+' || ch == '-' || Character.isDigit(ch)) {
                    if (Polys.costantCheck(k)) {
                        re.add(new Contant(k));
                    }
                } else {
                    Polys.wrong();
                }
            }
        }
        return re;
    }
}
