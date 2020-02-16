package work.hennig.rapid_horn.transformations;

import work.hennig.rapid_horn.cfg.*;
import work.hennig.rapid_horn.expression.Expression;

import java.util.List;

public class CFG2Horn implements CFGVisitor {

    private List<Expression> rules;

    @Override
    public void visit(Assignment assignment) {

    }

    @Override
    public void visit(CFG cfg) {

    }

    @Override
    public void visit(Condition condition) {

    }

    @Override
    public void visit(Location location) {

    }

    @Override
    public void visit(Skip skip) {

    }
}
