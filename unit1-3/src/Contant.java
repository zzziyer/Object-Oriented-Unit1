public class Contant extends Base {

    public Contant(String self) {
        this.setType(8);
        this.setSelf(self);
    }

    @Override
    public String div(Base base) {
        return "0";
    }
}
