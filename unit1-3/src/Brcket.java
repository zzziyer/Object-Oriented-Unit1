public class Brcket extends Base {
    public Brcket(String a, Base left, String self) {
        this.setLeftS(a);
        this.setType(9);
        this.setLeft(left);
        this.setSelf(self);
    }

    @Override
    public String div(Base base) {
        return "(" + base.getLeftChild().div(base.getLeftChild()) + ")";
    }
}
