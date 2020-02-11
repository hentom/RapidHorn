package work.hennig.rapid_horn.ast;

import java.util.List;

public class Program {

    private List<Function> functions;

    public Program(List<Function> functions) {
        this.functions = functions;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
