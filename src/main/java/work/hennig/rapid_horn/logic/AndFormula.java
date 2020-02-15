package work.hennig.rapid_horn.logic;

public class AndFormula extends BinaryFormula {

    public AndFormula(Formula left, Formula right) {
        super(left, right);
    }

    @Override
    public void accept(FormulaVisitor visitor) {
        visitor.visit(this);
    }
}
