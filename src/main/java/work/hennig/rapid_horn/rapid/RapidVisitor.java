package work.hennig.rapid_horn.rapid;

public interface RapidVisitor {

    void visit(AssertStatement statement);
    void visit(AssignmentStatement statement);
    void visit(AssumeStatement statement);
    void visit(ConditionalStatement statement);
    void visit(Declaration statement);
    void visit(DeclarationStatement statement);
    void visit(Function function);
    void visit(Program program);
    void visit(SkipStatement statement);
    void visit(WhileStatement statement);
}
