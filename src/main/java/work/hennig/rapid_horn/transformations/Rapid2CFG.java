package work.hennig.rapid_horn.transformations;

import work.hennig.rapid_horn.cfg.*;
import work.hennig.rapid_horn.expression.Expression;
import work.hennig.rapid_horn.rapid.*;

import java.util.LinkedList;
import java.util.List;

public class Rapid2CFG implements RapidVisitor {

    private List<Location> locations;
    private List<Transition> transitions;
    private List<Declaration> declarations;
    private final Location first;
    private final Location error;
    private Location last;

    private Rapid2CFG(Location first) {
        this.locations = new LinkedList<>();
        this.transitions = new LinkedList<>();
        this.declarations = new LinkedList<>();
        this.first = first;
        this.error = new Location("error", new LinkedList<>());
        this.last = first;

        this.locations.add(this.error);
        this.locations.add(this.first);
        this.declarations.addAll(this.first.getLiveVariables());
    }

    public static CFG transform(Program program) {
        CFG result = new CFG();
        for (Function function : program.getFunctions()) {
            CFG cfg = transform(function);
            if (function.getId().equals("main")) {
                if (result.hasStart()) {
                    throw new UnsupportedOperationException("program has more than one entry point");
                }
                result.add(cfg, cfg.getStartLocation());
            } else {
                result.add(cfg);
            }
        }
        return result;
    }

    public static CFG transform(Function function) {
        Location start = new Location(new LinkedList<>(function.getParameters()));
        Rapid2CFG transformer = new Rapid2CFG(start);
        for (Statement statement : function.getStatements()) {
            statement.accept(transformer);
        }

        return new CFG(transformer.locations, transformer.transitions, transformer.declarations, start,
                transformer.error);
    }

    @Override
    public void visit(AssertStatement statement) {
        Location target = new Location(new LinkedList<>(last.getLiveVariables()));
        locations.add(target);
        Transition toTarget = new Condition(last, target, statement.getCondition());
        transitions.add(toTarget);
        last.addOutgoingTransition(toTarget);
        target.addIncomingTransition(toTarget);
        Transition toError = new Condition(last, error, NegateExpression.negate(statement.getCondition()));
        transitions.add(toError);
        last.addOutgoingTransition(toError);
        error.addIncomingTransition(toError);
        last = target;
    }

    @Override
    public void visit(AssignmentStatement statement) {
        Variable variable;
        if (statement.hasIndex()) {
            variable = new Array(statement.getId(), statement.getIndex());
        } else {
            variable = new Variable(statement.getId());
        }

        Location target = new Location(new LinkedList<>(last.getLiveVariables()));
        locations.add(target);
        Transition transition = new Assignment(last, target, variable, statement.getExpression());
        transitions.add(transition);
        last.addOutgoingTransition(transition);
        target.addIncomingTransition(transition);
        last = target;
    }

    @Override
    public void visit(AssumeStatement statement) {
        Location target = new Location(new LinkedList<>(last.getLiveVariables()));
        locations.add(target);
        Transition transition = new Condition(last, target, statement.getCondition());
        transitions.add(transition);
        last.addOutgoingTransition(transition);
        target.addIncomingTransition(transition);
        last = target;
    }

    @Override
    public void visit(ConditionalStatement statement) {
        Location initial = last;
        Location afterCondition = new Location(new LinkedList<>(initial.getLiveVariables()));
        locations.add(afterCondition);

        Transition condition = new Condition(initial, afterCondition, statement.getCondition());
        transitions.add(condition);
        initial.addOutgoingTransition(condition);
        afterCondition.addIncomingTransition(condition);

        last = afterCondition;
        for (Statement s : statement.getIfBlock()) {
            s.accept(this);
        }
        Location lastIfBlock = last;

        Expression negatedExpression = NegateExpression.negate(statement.getCondition());
        if (statement.hasElseBlock()) {
            Location afterNegation = new Location(new LinkedList<>(initial.getLiveVariables()));
            locations.add(afterNegation);
            Transition negatedCondition = new Condition(initial, afterNegation, negatedExpression);
            transitions.add(negatedCondition);
            initial.addOutgoingTransition(negatedCondition);
            afterNegation.addIncomingTransition(negatedCondition);

            last = afterNegation;
            for (Statement s : statement.getElseBlock()) {
                s.accept(this);
            }
            Location lastElseBlock = last;

            Location end = new Location(new LinkedList<>(initial.getLiveVariables()));
            locations.add(end);

            Transition endIfBlock = new Skip(lastIfBlock, end);
            transitions.add(endIfBlock);
            lastIfBlock.addOutgoingTransition(endIfBlock);
            end.addIncomingTransition(endIfBlock);

            Transition endElseBlock = new Skip(lastElseBlock, end);
            transitions.add(endElseBlock);
            lastElseBlock.addOutgoingTransition(endElseBlock);
            end.addIncomingTransition(endElseBlock);

            last = end;
        } else {
            Transition negatedCondition = new Condition(initial, lastIfBlock, negatedExpression);
            transitions.add(negatedCondition);
            initial.addOutgoingTransition(negatedCondition);
            lastIfBlock.addIncomingTransition(negatedCondition);
            last = lastIfBlock; // not necessary, just for readability
        }
    }

    @Override
    public void visit(Declaration statement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(DeclarationStatement statement) {
        if (statement.hasExpression()) {
            Variable variable;
            if (statement.isArray()) {
                throw new UnsupportedOperationException("cannot initialize array in declaration");
            } else {
                variable = new Variable(statement.getId());
            }

            Location target = new Location(new LinkedList<>(last.getLiveVariables()));
            locations.add(target);
            Transition transition = new Assignment(last, target, variable, statement.getExpression());
            transitions.add(transition);
            last.addOutgoingTransition(transition);
            target.addIncomingTransition(transition);
            last = target;
        }

        Declaration declaration = new Declaration(statement);
        last.getLiveVariables().add(declaration);
        declarations.add(declaration);
    }

    @Override
    public void visit(Function function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(Program program) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(SkipStatement statement) {
        // Nothing to do here
    }

    @Override
    public void visit(WhileStatement statement) {
        Location initial = last;
        Location afterCondition = new Location(new LinkedList<>(initial.getLiveVariables()));
        locations.add(afterCondition);

        Transition condition = new Condition(initial, afterCondition, statement.getCondition());
        transitions.add(condition);
        initial.addOutgoingTransition(condition);
        afterCondition.addIncomingTransition(condition);

        last = afterCondition;
        for (Statement s : statement.getBlock()) {
            s.accept(this);
        }
        Location lastWhileBlock = last;

        Transition loopTransition = new Skip(lastWhileBlock, initial);
        transitions.add(loopTransition);
        lastWhileBlock.addOutgoingTransition(loopTransition);
        initial.addIncomingTransition(loopTransition);

        Location end = new Location(new LinkedList<>(initial.getLiveVariables()));
        locations.add(end);
        Expression negatedExpression = NegateExpression.negate(statement.getCondition());
        Transition negatedCondition = new Condition(initial, end, negatedExpression);
        transitions.add(negatedCondition);
        initial.addOutgoingTransition(negatedCondition);
        end.addIncomingTransition(negatedCondition);
        last = end;
    }
}
