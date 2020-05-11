public class Cos extends Base {
    public Cos(String a, Base left, String self) {
        this.setLeftS(a);
        this.setType(2);
        this.setLeft(left);
        this.setSelf(self);
    }

    @Override
    public String div(Base base) {
        int t = base.getLeftChild().getType();
        if (t >= 5 && t <= 7) {
            // System.out.println("coswrong");
            Polys.wrong();
        }
        return "-1*sin(" + base.getLeftString() + ")*" +
                base.getLeftChild().div(base.getLeftChild());
    }
}
