import java.math.BigInteger;

public class Pow extends Base {

    public Pow(String a, String b, Base left, Base right,
               String self) {
        this.setLeftS(a);
        this.setRightS(b);
        this.setType(4);
        this.setLeft(left);
        this.setRight(right);
        this.setSelf(self);
    }

    @Override
    public String div(Base base) {
        int t = base.getLeftChild().getType();
        BigInteger k = new BigInteger(base.getRightString());
        if (k.abs().compareTo(BigInteger.valueOf(10000)) > 0 ||
                !(t >= 1 && t <= 3)) {
            //System.out.println("powwrong");
            Polys.wrong();
        }
        if (Integer.valueOf(base.getRightString()) == 0) {
            return "0";
        } else {
            return k + "*" + base.getLeftString() + "^" +
                    k.subtract(BigInteger.valueOf(1)) + "*"
                    + base.getLeftChild().div(base.getLeftChild());
        }
    }
}
