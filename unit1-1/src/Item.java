import java.math.BigInteger;

public class Item {
    private BigInteger coe;
    private BigInteger index;

    public Item(BigInteger c, BigInteger i) {
        this.coe = c;
        this.index = i;
    }

    public Item(String s) {
        int i;
        int tag = 0;
        StringBuffer c = new StringBuffer("0");
        StringBuffer inde = new StringBuffer("0");
        BigInteger cc = BigInteger.valueOf(0);
        BigInteger iinde = BigInteger.valueOf(0);
        BigInteger flag = BigInteger.valueOf(1);
        for (i = 0; i < s.length(); i++) {
            for (; i < s.length() && s.charAt(i) != 'x'; i++) {
                if (s.charAt(i) == '-') {
                    flag = flag.multiply(BigInteger.valueOf(-1));
                } else if (Character.isDigit(s.charAt(i))) {
                    c.append(s.charAt(i));
                    tag = 1;
                }
            }
            if (tag == 0) {
                c = new StringBuffer("1");
            }
            cc = new BigInteger(c.toString());
            cc = cc.multiply(flag);
            flag = BigInteger.valueOf(1);
            for (; i < s.length(); i++) {
                if (s.charAt(i) == '-') {
                    flag = flag.multiply(BigInteger.valueOf(-1));
                } else if (Character.isDigit(s.charAt(i))) {
                    inde.append(s.charAt(i));
                }
            }
            if (s.charAt(s.length() - 1) == 'x') {
                inde = new StringBuffer("1");
            }
            iinde = new BigInteger(inde.toString());
            iinde = iinde.multiply(flag);
        }
        //System.out.println(cc+"   "+iinde);
        this.coe = cc;
        this.index = iinde;
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

    public Item derivation(Item e) {
        //System.out.println(e.coe+"  "+e.index);
        BigInteger c = e.coe;
        BigInteger i;
        if ((e.index.compareTo(BigInteger.ZERO)) != 0) { //TODO
            c = c.multiply(e.index);
            i = e.index.subtract(BigInteger.valueOf(1));
        } else {
            c = BigInteger.valueOf(0);
            i = BigInteger.valueOf(0);
        }
        return new Item(c, i);
    }

    public int print(Item item) {
        BigInteger a = BigInteger.valueOf(0);
        BigInteger b = BigInteger.valueOf(1);
        BigInteger c = BigInteger.valueOf(-1);
        if (item.coe.compareTo(a) == 0) {
            return 0;
        }
        if (item.index.compareTo(a) != 0 && item.index.compareTo(b) != 0) {
            if (item.coe.compareTo(b) == 0) {
                System.out.print("+x^" + item.index);
            } else if (item.coe.compareTo(c) == 0) {
                System.out.print("-x^" + item.index);
            } else if (item.coe.compareTo(a) > 0) {
                System.out.print("+" + item.coe + "*x^" + item.index);
            } else {
                System.out.print(item.coe + "*x^" + item.index);
            }
        } else if (item.index.compareTo(b) == 0) {
            if (item.coe.compareTo(b) == 0) {
                System.out.print("+x");
            } else if (item.coe.compareTo(c) == 0) {
                System.out.print("-x");
            } else if (item.coe.compareTo(a) > 0) {
                System.out.print("+" + item.coe + "*x");
            } else {
                System.out.print(item.coe + "*x");
            }
        } else {
            if (item.coe.compareTo(a) > 0) {
                System.out.print("+" + item.coe);
            } else {
                System.out.print(item.coe);
            }
        }
        return 1;
    }
}
