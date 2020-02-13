package work.hennig.rapid_horn.rapid;

import java.util.List;

public class Program {

    private List<Function> functions;

    public Program(List<Function> functions) {
        this.functions = functions;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void accept(RapidVisitor visitor) {
        visitor.visit(this);
    }
}
