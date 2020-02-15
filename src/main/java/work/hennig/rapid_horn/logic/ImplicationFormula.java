package work.hennig.rapid_horn.logic;

public class ImplicationFormula extends BinaryFormula {

    public ImplicationFormula(Formula left, Formula right) {
        super(left, right);
    }

    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visit(this);
    }
}
