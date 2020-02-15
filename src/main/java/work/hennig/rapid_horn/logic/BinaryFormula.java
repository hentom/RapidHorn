package work.hennig.rapid_horn.logic;

public abstract class BinaryFormula extends Formula {

    protected Formula left;
    protected Formula right;

    protected BinaryFormula(Formula left, Formula right) {
        this.left = left;
        this.right = right;
    }

    public Formula getLeft() {
        return left;
    }

    public Formula getRight() {
        return right;
    }
}
