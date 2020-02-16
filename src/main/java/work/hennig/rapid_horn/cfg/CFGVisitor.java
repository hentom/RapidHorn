package work.hennig.rapid_horn.cfg;

public interface CFGVisitor {

    void visit(Assignment assignment);
    void visit(CFG cfg);
    void visit(Condition condition);
    void visit(Location location);
    void visit(Skip skip);
}
