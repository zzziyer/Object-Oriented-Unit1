public abstract class Base implements Node {
    private Base left = null;
    private Base right = null;

    private String leftS;
    private String rightS;
    private String self;

    private boolean leftChild = false;
    private boolean rightChild = false;
    private boolean hasChild = false;

    private int type;

    //1:x, 2:cos, 3:sin 4:pow 5:+ 6:- 7:* 8:constant 9:()
    public Base() {

    }

    @Override
    public Base getLeftChild() {
        return this.left;
    }

    @Override
    public Base getRightChild() {
        return this.right;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public String getLeftString() {
        return this.leftS;
    }

    @Override
    public String getRightString() {
        return this.rightS;
    }

    @Override
    public String getSelfString() {
        return this.self;
    }

    @Override
    public String div(Base base) {
        return null;
    }

    public void setLeft(Base left) {
        this.left = left;
    }

    public void setRight(Base right) {
        this.right = right;
    }

    public void setLeftS(String leftS) {
        this.leftS = leftS;
    }

    public void setRightS(String rightS) {
        this.rightS = rightS;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public void setType(int type) {
        this.type = type;
    }
}
