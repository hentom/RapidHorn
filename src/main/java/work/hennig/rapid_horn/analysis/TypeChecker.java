package work.hennig.rapid_horn.analysis;

import work.hennig.rapid_horn.expression.*;
import work.hennig.rapid_horn.rapid.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TypeChecker implements RapidVisitor, ExpressionVisitor {

    private Stack<List<Declaration>> frames;
    private Type expectedType;

    private TypeChecker() {
        this.frames = new Stack<>();
    }

    public static boolean check(Program program) {
        TypeChecker checker = new TypeChecker();
        try {
            program.accept(checker);
        } catch (TypeException e) {
            System.err.println("WARNING: " + e.getMessage());
            return false;
        }
        return true;
    }



    private Declaration findDeclaration(String identifier) {
        for (List<Declaration> frame : frames) {
            for (Declaration declaration : frame) {
                if (declaration.getId().equals(identifier)) {
                    return declaration;
                }
            }
        }
        return null;
    }

    @Override
    public void visit(AssignmentStatement statement) throws TypeException {
        Declaration declaration = findDeclaration(statement.getId());
        if (declaration == null) {
            throw new TypeException("\'" + statement.getId() +"\' is assigned to but not declared");
        }
        if (declaration.isConstant()) {
            throw new TypeException("constant \'" + statement.getId() + "\' cannot be assigned to");
        }

        if (statement.hasIndex()) {
            if (!declaration.isArray()) {
                throw new TypeException("\'" + statement.getId() + "\' is not declared as array but indexed");
            }
            expectedType = Type.INTEGER;
            statement.getIndex().accept(this);
        }

        expectedType = declaration.getType();
        statement.getExpression().accept(this);
    }

    @Override
    public void visit(ConditionalStatement statement) {
        expectedType = Type.BOOLEAN;
        statement.getCondition().accept(this);

        frames.push(new LinkedList<>());
        for (Statement s : statement.getIfBlock()) {
            s.accept(this);
        }
        frames.pop();

        if (statement.hasElseBlock()) {
            frames.push(new LinkedList<>());
            for (Statement s : statement.getElseBlock()) {
                s.accept(this);
            }
            frames.pop();
        }
    }

    @Override
    public void visit(Declaration statement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(DeclarationStatement statement) {
        if (statement.hasExpression()) {
            expectedType = statement.getType();
            statement.getExpression().accept(this);
        }

        if (findDeclaration(statement.getId()) != null) {
            throw new TypeException("identifier \'" + statement.getId() + "\' declared twice");
        }

        frames.peek().add(new Declaration(statement));
    }

    @Override
    public void visit(Function function) {
        frames.push(function.getParameters());
        for (Statement statement : function.getStatements()) {
            statement.accept(this);
        }
        frames.pop();
    }

    @Override
    public void visit(Program program) {
        for (Function function : program.getFunctions()) {
            function.accept(this);
        }
    }

    @Override
    public void visit(SkipStatement statement) { }

    @Override
    public void visit(WhileStatement statement) {
        expectedType = Type.BOOLEAN;
        statement.getCondition().accept(this);

        frames.push(new LinkedList<>());
        for (Statement s : statement.getBlock()) {
            s.accept(this);
        }
        frames.pop();
    }

    ///////////////////////////////////////////////////////////////////
    // Arithmetic expressions
    //

    @Override
    public void visit(AdditionExpression expression) {
        if (expectedType != Type.INTEGER) {
            throw new TypeException("expected type " + expectedType.toString() + " but found addition");
        }

        expression.getLeft().accept(this);
        expression.getRight().accept(this);
    }

    @Override
    public void visit(IntegerLiteralExpression expression) {
        if (expectedType != Type.INTEGER) {
            throw new TypeException("expected type " + expectedType.toString() + " but found integer literal " +
                    expression.getValue());
        }
    }

    @Override
    public void visit(ModuloExpression expression) {
        if (expectedType != Type.INTEGER) {
            throw new TypeException("expected type " + expectedType.toString() + " but found modulo");
        }

        expression.getLeft().accept(this);
        expression.getRight().accept(this);
    }

    @Override
    public void visit(MultiplicationExpression expression) {
        if (expectedType != Type.INTEGER) {
            throw new TypeException("expected type " + expectedType.toString() + " but found multiplication");
        }

        expression.getLeft().accept(this);
        expression.getRight().accept(this);
    }

    @Override
    public void visit(NegationExpression expression) {
        if (expectedType != Type.INTEGER) {
            throw new TypeException("expected type " + expectedType.toString() + " but found unary minus");
        }

        expression.getSubexpression().accept(this);
    }

    @Override
    public void visit(SubtractionExpression expression) {
        if (expectedType != Type.INTEGER) {
            throw new TypeException("expected type " + expectedType.toString() + " but found subtraction");
        }

        expression.getLeft().accept(this);
        expression.getRight().accept(this);
    }

    @Override
    public void visit(VariableExpression expression) {
        Declaration declaration = findDeclaration(expression.getId());
        if (declaration == null) {
            throw new TypeException("\'" + expression.getId() +"\' is used but not declared");
        }
        if (declaration.getType() != expectedType) {
            throw new TypeException("expected type " + expectedType.toString() + " but found variable of type " +
                    declaration.getType());
        }
    }

    ///////////////////////////////////////////////////////////////////
    // Boolean expressions
    //

    @Override
    public void visit(AndExpression expression) {
        if (expectedType != Type.BOOLEAN) {
            throw new TypeException("expected type " + expectedType.toString() + " but found logical and");
        }

        expression.getLeft().accept(this);
        expression.getRight().accept(this);
    }

    @Override
    public void visit(BooleanLiteralExpression expression) {
        if (expectedType != Type.BOOLEAN) {
            throw new TypeException("expected type " + expectedType.toString() + " but found boolean literal");
        }
    }

    @Override
    public void visit(EqualExpression expression) {
        if (expectedType != Type.BOOLEAN) {
            throw new TypeException("expected type " + expectedType.toString() + " but found equality relation");
        }

        Type outerType = expectedType;
        expectedType = Type.INTEGER;

        expression.getLeft().accept(this);
        expression.getRight().accept(this);

        expectedType = outerType;
    }

    @Override
    public void visit(GreaterEqualExpression expression) {
        if (expectedType != Type.BOOLEAN) {
            throw new TypeException("expected type " + expectedType.toString() + " but found greater or equal");
        }

        Type outerType = expectedType;
        expectedType = Type.INTEGER;

        expression.getLeft().accept(this);
        expression.getRight().accept(this);

        expectedType = outerType;
    }

    @Override
    public void visit(GreaterThanExpression expression) {
        if (expectedType != Type.BOOLEAN) {
            throw new TypeException("expected type " + expectedType.toString() + " but found greater");
        }

        Type outerType = expectedType;
        expectedType = Type.INTEGER;

        expression.getLeft().accept(this);
        expression.getRight().accept(this);

        expectedType = outerType;
    }

    @Override
    public void visit(LessEqualExpression expression) {
        if (expectedType != Type.BOOLEAN) {
            throw new TypeException("expected type " + expectedType.toString() + " but found less or equal");
        }

        Type outerType = expectedType;
        expectedType = Type.INTEGER;

        expression.getLeft().accept(this);
        expression.getRight().accept(this);

        expectedType = outerType;
    }

    @Override
    public void visit(LessThanExpression expression) {
        if (expectedType != Type.BOOLEAN) {
            throw new TypeException("expected type " + expectedType.toString() + " but found less");
        }

        Type outerType = expectedType;
        expectedType = Type.INTEGER;

        expression.getLeft().accept(this);
        expression.getRight().accept(this);

        expectedType = outerType;
    }

    @Override
    public void visit(NotEqualExpression expression) {
        if (expectedType != Type.BOOLEAN) {
            throw new TypeException("expected type " + expectedType.toString() + " but found not equal relation");
        }

        Type outerType = expectedType;
        expectedType = Type.INTEGER;

        expression.getLeft().accept(this);
        expression.getRight().accept(this);

        expectedType = outerType;
    }

    @Override
    public void visit(NotExpression expression) {
        if (expectedType != Type.BOOLEAN) {
            throw new TypeException("expected type " + expectedType.toString() + " but found logical negation");
        }

        expression.getSubexpression().accept(this);
    }

    @Override
    public void visit(OrExpression expression) {
        if (expectedType != Type.BOOLEAN) {
            throw new TypeException("expected type " + expectedType.toString() + " but found logical or");
        }

        expression.getLeft().accept(this);
        expression.getRight().accept(this);
    }
}
