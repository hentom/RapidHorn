package work.hennig.rapid_horn.logic;

public interface FormulaVisitor {

    void visit(AndFormula formula);
    void visit(ImplicationFormula formula);
}
