public class X extends Base {

    public X(String self) {
        this.setType(1);
        this.setSelf(self);
    }

    @Override
    public String div(Base base) {
        return "1";
    }
}
