package work.hennig.rapid_horn.transformations;

import work.hennig.rapid_horn.cfg.*;
import work.hennig.rapid_horn.cfg.Declaration;
import work.hennig.rapid_horn.expression.Expression;
import work.hennig.rapid_horn.rapid.*;

import java.util.LinkedList;
import java.util.List;

public class Rapid2CFG implements RapidVisitor {

    private final Location first;
    private final Location error;
    private Location last;

    private Rapid2CFG(Location first) {
        this.first = first;
        this.error = new Location(new LinkedList<>());
        this.last = first;
    }

    public static List<Location> transform(Program program) {
        List<Location> result = new LinkedList<>();
        for (Function function : program.getFunctions()) {
            result.add(transform(function));
        }
        return result;
    }

    public static Location transform(Function function) {
        List<Declaration> declarations = new LinkedList<>();
        for (var declaration : function.getParameters()) {
            declarations.add(new Declaration(declaration.getId(), declaration.isArray()));
        }

        Rapid2CFG transformer = new Rapid2CFG(new Location(declarations));
        for (Statement statement : function.getStatements()) {
            statement.accept(transformer);
        }

        return transformer.first;
    }

    @Override
    public void visit(AssertStatement statement) {
        Location target = new Location(new LinkedList<>(last.getLiveVariables()));
        Transition toTarget = new Condition(last, target, statement.getCondition());
        last.addOutgoingTransition(toTarget);
        target.addIncomingTransition(toTarget);
        Transition toError = new Condition(last, target, NegateExpression.negate(statement.getCondition()));
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
        Transition transition = new Assignment(last, target, variable, statement.getExpression());
        last.addOutgoingTransition(transition);
        target.addIncomingTransition(transition);
        last = target;
    }

    @Override
    public void visit(AssumeStatement statement) {
        Location target = new Location(new LinkedList<>(last.getLiveVariables()));
        Transition transition = new Condition(last, target, statement.getCondition());
        last.addOutgoingTransition(transition);
        target.addIncomingTransition(transition);
        last = target;
    }

    @Override
    public void visit(ConditionalStatement statement) {
        Location initial = last;
        Location afterCondition = new Location(new LinkedList<>(initial.getLiveVariables()));

        Transition condition = new Condition(initial, afterCondition, statement.getCondition());
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
            Transition negatedCondition = new Condition(initial, afterNegation, negatedExpression);
            initial.addOutgoingTransition(negatedCondition);
            afterNegation.addIncomingTransition(negatedCondition);

            last = afterNegation;
            for (Statement s : statement.getElseBlock()) {
                s.accept(this);
            }
            Location lastElseBlock = last;

            Location end = new Location(new LinkedList<>(initial.getLiveVariables()));

            Transition endIfBlock = new Skip(lastIfBlock, end);
            lastIfBlock.addOutgoingTransition(endIfBlock);
            end.addIncomingTransition(endIfBlock);

            Transition endElseBlock = new Skip(lastElseBlock, end);
            lastElseBlock.addOutgoingTransition(endElseBlock);
            end.addIncomingTransition(endElseBlock);

            last = end;
        } else {
            Transition negatedCondition = new Condition(initial, lastIfBlock, negatedExpression);
            initial.addOutgoingTransition(negatedCondition);
            lastIfBlock.addIncomingTransition(negatedCondition);
            last = lastIfBlock; // not necessary, just for readability
        }
    }

    @Override
    public void visit(work.hennig.rapid_horn.rapid.Declaration statement) {
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
            Transition transition = new Assignment(last, target, variable, statement.getExpression());
            last.addOutgoingTransition(transition);
            target.addIncomingTransition(transition);
            last = target;
        }

        last.getLiveVariables().add(new Declaration(statement.getId(), statement.isArray()));
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

        Transition condition = new Condition(initial, afterCondition, statement.getCondition());
        initial.addOutgoingTransition(condition);
        afterCondition.addIncomingTransition(condition);

        last = afterCondition;
        for (Statement s : statement.getBlock()) {
            s.accept(this);
        }
        Location lastWhileBlock = last;

        Transition loopTransition = new Skip(lastWhileBlock, initial);
        lastWhileBlock.addOutgoingTransition(loopTransition);
        initial.addIncomingTransition(loopTransition);

        Location end = new Location(new LinkedList<>(initial.getLiveVariables()));
        Expression negatedExpression = NegateExpression.negate(statement.getCondition());
        Transition negatedCondition = new Condition(initial, end, negatedExpression);
        initial.addOutgoingTransition(negatedCondition);
        end.addIncomingTransition(negatedCondition);
        last = end;
    }
}
