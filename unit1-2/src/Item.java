import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Item {
    private BigInteger coe = BigInteger.valueOf(0);
    private BigInteger index = BigInteger.valueOf(0);
    private BigInteger cos = BigInteger.valueOf(0);
    private BigInteger sin = BigInteger.valueOf(0);

    public Item() {

    }

    public Item(BigInteger coe, BigInteger index,
                BigInteger cos, BigInteger sin) {
        this.coe = coe;
        this.index = index;
        this.cos = cos;
        this.sin = sin;
    }

    public Item(ArrayList<String> a) {
        BigInteger coeTemp = BigInteger.valueOf(1);
        Pattern px = Pattern.compile("x");
        Pattern pcos = Pattern.compile("cos");
        Pattern psin = Pattern.compile("sin");
        ArrayList<String> xx = new ArrayList<>();
        ArrayList<String> cc = new ArrayList<>();
        ArrayList<String> ss = new ArrayList<>();
        for (String s :
                a) {
            Matcher x = px.matcher(s);
            Matcher cos = pcos.matcher(s);
            Matcher sin = psin.matcher(s);
            if (cos.find()) {
                cc.add(s);
                continue;
            } else if (sin.find()) {
                ss.add(s);
                continue;
            } else if (x.find()) {
                xx.add(s);
                continue;
            } else {
                coeTemp = coeTemp.multiply(new BigInteger(s.substring(1)));
            }
        }
        this.coe = coeTemp;
        //System.out.println(this.coe);
        BigInteger cosTemp = BigInteger.valueOf(0);
        for (String s :
                cc) {
            int i = s.indexOf(94);//查找‘^’
            //System.out.println(i);
            if (i == -1) {
                cosTemp = cosTemp.add(BigInteger.valueOf(1));
            } else {
                cosTemp = cosTemp.add(new BigInteger(s.substring(i + 1)));
            }
        }
        this.cos = cosTemp;
        //System.out.println(this.cos);
        BigInteger sinTemp = BigInteger.valueOf(0);
        for (String s :
                ss) {
            int i = s.indexOf(94);//查找‘^’
            if (i == -1) {
                sinTemp = sinTemp.add(BigInteger.valueOf(1));
            } else {
                sinTemp = sinTemp.add(new BigInteger(s.substring(i + 1)));
            }
        }
        this.sin = sinTemp;
        //System.out.println(this.sin);
        BigInteger powerTemp = BigInteger.valueOf(0);
        for (String s :
                xx) {
            int i = s.indexOf(94);//查找‘^’
            if (i == -1) {
                powerTemp = powerTemp.add(BigInteger.valueOf(1));
            } else {
                powerTemp = powerTemp.add(new BigInteger(s.substring(i + 1)));
            }
        }
        this.index = powerTemp;
        //System.out.println(this.index);
        //System.out.println(this.coe+" "+this.index+" "+this.cos+" "+this.sin);
    }

    public BigInteger getCoe() {
        return coe;
    }

    public void setCoe(BigInteger coe) {
        this.coe = coe;
    }

    public BigInteger getIndex() {
        return index;
    }

    public BigInteger getCos() {
        return cos;
    }

    public BigInteger getSin() {
        return sin;
    }

    public ArrayList<Item> derivation(Item e) {
        Item add = new Item();
        ArrayList<Item> back = new ArrayList<>();
        BigInteger coeTemp = e.coe;
        BigInteger indexTemp = e.index;
        BigInteger cosTemp = e.cos;
        BigInteger sinTemp = e.sin;
        if ((e.coe.compareTo(BigInteger.ZERO)) == 0) {
            back.add(new Item(BigInteger.valueOf(0), BigInteger.valueOf(0),
                    BigInteger.valueOf(0), BigInteger.valueOf(0)));
            return back;
        }
        if ((e.index.compareTo(BigInteger.ZERO)) != 0) {
            coeTemp = coeTemp.multiply(e.index);
            indexTemp = e.index.subtract(BigInteger.valueOf(1));
            back.add(new Item(coeTemp, indexTemp, e.cos, e.sin));
        }
        coeTemp = e.coe;
        if ((e.cos.compareTo(BigInteger.ZERO)) != 0) {
            coeTemp = coeTemp.multiply(e.cos);
            coeTemp = coeTemp.multiply(BigInteger.valueOf(-1));
            cosTemp = e.cos.subtract(BigInteger.valueOf(1));
            sinTemp = e.sin.add(BigInteger.valueOf(1));
            back.add(new Item(coeTemp, e.index, cosTemp, sinTemp));
        }
        coeTemp = e.coe;
        if ((e.sin.compareTo(BigInteger.ZERO)) != 0) {
            //System.out.println(e.coe+" "+e.index+" "+e.cos+" "+e.sin);
            coeTemp = coeTemp.multiply(e.sin);
            sinTemp = e.sin.subtract(BigInteger.valueOf(1));
            cosTemp = e.cos.add(BigInteger.valueOf(1));
            //System.out.println(coeTemp+" "+e.index+" "+cosTemp+" "+sinTemp);
            back.add(new Item(coeTemp, e.index, cosTemp, sinTemp));
        }
        if (e.sin.compareTo(BigInteger.ZERO) == 0 &&
                e.cos.compareTo(BigInteger.ZERO) == 0 &&
                e.index.compareTo(BigInteger.ZERO) == 0 &&
                e.coe.compareTo(BigInteger.ZERO) != 0) {
            back.add(new Item(BigInteger.valueOf(0), BigInteger.valueOf(0),
                    BigInteger.valueOf(0), BigInteger.valueOf(0)));
        }
        return back;
    }

    public int print(Item item) {
        BigInteger a = BigInteger.valueOf(0);
        BigInteger b = BigInteger.valueOf(1);
        BigInteger c = BigInteger.valueOf(-1);
        int tag = 0;
        int tag1 = 0;
        int tag2 = 0;
        //System.out.println(item.coe);
        if (item.coe.compareTo(a) == 0) {
            return 0;
        }
        if (item.coe.compareTo(b) == 0 &&
                !(item.index.compareTo(a) == 0 && item.cos.compareTo(a) == 0 &&
                        item.sin.compareTo(a) == 0)) {
            System.out.print("+");
        } else if (item.coe.compareTo(c) == 0 &&
                !(item.index.compareTo(a) == 0 &&
                        item.cos.compareTo(a) == 0 &&
                        item.sin.compareTo(a) == 0)) {
            System.out.print("-");
        } else if (item.coe.compareTo(a) > 0) {
            System.out.print("+" + item.coe);
            tag = 1;
        } else if (item.coe.compareTo(a) < 0) {
            System.out.print(item.coe);
            tag = 1;
        }

        if (item.index.compareTo(a) != 0) {
            if (tag == 0) {
                System.out.print("x");
            } else {
                System.out.print("*x");
            }
            if (item.index.compareTo(b) != 0) {
                System.out.print("^" + item.index);
            }
            tag1 = 1;
        }
        if (item.cos.compareTo(a) != 0) {
            if (tag == 0 && tag1 == 0) {
                System.out.print("cos(x)");
            } else {
                System.out.print("*cos(x)");
            }
            if (item.cos.compareTo(b) != 0) {
                System.out.print("^" + item.cos);
            }
            tag2 = 1;
        }
        if (item.sin.compareTo(a) != 0) {
            if (tag == 0 && tag1 == 0 && tag2 == 0) {
                System.out.print("sin(x)");
            } else {
                System.out.print("*sin(x)");
            }
            if (item.sin.compareTo(b) != 0) {
                System.out.print("^" + item.sin);
            }
        }
        return 1;
    }
}
