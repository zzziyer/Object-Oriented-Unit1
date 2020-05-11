public class Add extends Base {

    public Add(String a, String b, Base left, Base right,
               String self) {
        this.setLeftS(a);
        this.setRightS(b);
        this.setType(5);
        this.setLeft(left);
        this.setRight(right);
        this.setSelf("(" + self + ")");
    }

    @Override
    public String div(Base base) {
        return "(" + base.getLeftChild().div(base.getLeftChild()) + "+" +
                base.getRightChild().div(base.getRightChild()) + ")";
    }
}
