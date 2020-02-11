package work.hennig.rapid_horn.ast;

public class SkipStatement extends Statement {

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
