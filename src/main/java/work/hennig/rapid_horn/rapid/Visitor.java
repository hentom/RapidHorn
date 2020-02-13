package work.hennig.rapid_horn.rapid;

public abstract class Visitor {

    public abstract void visit(AssignmentStatement statement);
    public abstract void visit(ConditionalStatement statement);
    public abstract void visit(Declaration statement);
    public abstract void visit(DeclarationStatement statement);
    public abstract void visit(Function function);
    public abstract void visit(Program program);
    public abstract void visit(SkipStatement statement);
    public abstract void visit(WhileStatement statement);
}
