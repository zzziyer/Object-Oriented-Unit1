public class Sin extends Base {

    public Sin(String a, Base left, String self) {
        this.setLeftS(a);
        this.setType(3);
        this.setLeft(left);
        this.setSelf(self);
    }

    @Override
    public String div(Base base) {
        int t = base.getLeftChild().getType();
        if (t >= 5 && t <= 7) {
            //System.out.println(t);
            //System.out.println("sinwrong");
            Polys.wrong();
        }
        return "cos(" + base.getLeftString() + ")*" +
                base.getLeftChild().div(base.getLeftChild());
    }
}
