package work.hennig.rapid_horn.ast;

public abstract class Statement {

    public abstract void accept(ASTVisitor visitor);
}
