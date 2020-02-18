package work.hennig.rapid_horn.transformations;

import work.hennig.rapid_horn.cfg.*;
import work.hennig.rapid_horn.rapid.Declaration;

public class CFG2Horn implements CFGVisitor {

    private StringBuilder builder;

    private CFG2Horn() {
        this.builder = new StringBuilder();
    }

    public static void transform(CFG cfg) {
        CFG2Horn transformer = new CFG2Horn();
        cfg.accept(transformer);
        System.out.print(transformer.builder);
    }

    @Override
    public void visit(Assignment assignment) {
        builder.append("( rule ( => ");
        assignment.getSource().accept(this);
        builder.append(" ");

        Location target = assignment.getTarget();
        if (target.getLiveVariables().size() == 0) {
            builder.append(target.getIdentifier());
        } else {
            builder.append("( ");
            builder.append(target.getIdentifier());
            builder.append(" ");
            for (Declaration declaration : target.getLiveVariables()) {
                if (declaration.getId().equals(assignment.getVariable().getIdentifier())) {
                    if (declaration.isArray()) {
                        builder.append("( store ");
                        builder.append(declaration.getId());
                        builder.append(" ");
                        builder.append(Expression2SMTLIB.transform(((Array)assignment.getVariable()).getIndex()));
                        builder.append(" ");
                        builder.append(Expression2SMTLIB.transform(assignment.getExpression()));
                        builder.append(" )");
                    } else {
                        builder.append(Expression2SMTLIB.transform(assignment.getExpression()));
                    }
                } else {
                    builder.append(declaration.getId());
                }
                builder.append(" ");
            }
            builder.append(")");
        }
        builder.append(" ) )\n");
    }

    @Override
    public void visit(CFG cfg) {
        builder.append("( set-logic HORN )\n");

        for (Location location : cfg.getLocations()) {
            builder.append("( declare-rel ");
            builder.append(location.getIdentifier());
            builder.append(" ( ");
            for (Declaration declaration : location.getLiveVariables()) {
                if (declaration.isArray()) {
                    builder.append("( Array Int ");
                    builder.append(declaration.getType());
                    builder.append(" ) ");
                } else {
                    builder.append(declaration.getType());
                    builder.append(" ");
                }
            }
            builder.append(") )\n");
        }

        for (Declaration declaration : cfg.getDeclarations()) {
            builder.append("( declare-var ");
            builder.append(declaration.getId());
            builder.append(" ");
            if (declaration.isArray()) {
                builder.append("( Array Int ");
                builder.append(declaration.getType());
                builder.append(" ) ");
            } else {
                builder.append(declaration.getType());
            }
            builder.append(" )\n");
        }

        if (cfg.hasStart()) {
            Location start = cfg.getStartLocation();
            builder.append("( rule ");
            start.accept(this);
            builder.append(" )\n");
        }

        for (Transition transition : cfg.getTransitions()) {
            transition.accept(this);
        }

        for (Location error : cfg.getErrorLocations()) {
            builder.append("( query ");
            builder.append(error.getIdentifier());
            builder.append(" )\n");
        }
    }

    @Override
    public void visit(Condition condition) {
        builder.append("( rule ( => ( and ");
        condition.getSource().accept(this);
        builder.append(" ");
        builder.append(Expression2SMTLIB.transform(condition.getCondition()));
        builder.append(" ) ");
        condition.getTarget().accept(this);
        builder.append(" ) )\n");
    }

    @Override
    public void visit(Location location) {
        if (location.getLiveVariables().size() == 0) {
            builder.append(location.getIdentifier());
        } else {
            builder.append("( ");
            builder.append(location.getIdentifier());
            builder.append(" ");
            for (Declaration declaration : location.getLiveVariables()) {
                builder.append(declaration.getId());
                builder.append(" ");
            }
            builder.append(")");
        }
    }

    @Override
    public void visit(Skip skip) {
        builder.append("( rule ( => ");
        skip.getSource().accept(this);
        builder.append(" ");
        skip.getTarget().accept(this);
        builder.append(" ) )\n");
    }
}
