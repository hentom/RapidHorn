package work.hennig.rapid_horn.logic;

public abstract class Formula {

    public abstract void accept(FormulaVisitor visitor);
}
