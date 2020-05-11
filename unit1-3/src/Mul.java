public class Mul extends Base {

    public Mul(String a, String b, Base left, Base right,
               String self) {
        this.setLeftS(a);
        this.setRightS(b);
        this.setType(7);
        this.setLeft(left);
        this.setRight(right);
        this.setSelf(self);
    }

    @Override
    public String div(Base base) {
        return "(" + base.getLeftChild().div(base.getLeftChild()) + "*"
                + base.getRightString()
                + "+" +
                base.getRightChild().div(base.getRightChild()) + "*"
                + base.getLeftString() + ")";
    }
}
