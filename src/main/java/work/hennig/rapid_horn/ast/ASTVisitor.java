package work.hennig.rapid_horn.ast;

public abstract class ASTVisitor {

    public abstract void visit(ConditionalStatement Statement);
    public abstract void visit(DeclarationStatement Statement);
    public abstract void visit(DefinitionStatement statement);
    public abstract void visit(Function function);
    public abstract void visit(Program program);
    public abstract void visit(SkipStatement statement);
    public abstract void visit(WhileStatement statement);
}
