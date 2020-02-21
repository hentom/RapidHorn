package work.hennig.rapid_horn.analysis;

import work.hennig.rapid_horn.expression.*;
import work.hennig.rapid_horn.rapid.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TypeChecker implements RapidVisitor, ExpressionVisitor {

    private Stack<List<Declaration>> frames;
    private Type currentType;

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

    private void fail(String expected, String found) {
        throw new TypeException("expected " + expected + " but found " + found);
    }

    @Override
    public void visit(AssertStatement statement) {
        statement.getCondition().accept(this);
        if (currentType != Type.BOOLEAN) {
            fail("boolean condition in assert statement", "expression of type " + currentType);
        }
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
            statement.getIndex().accept(this);
            if (currentType != Type.INTEGER) {
                fail("index expression of type Int", "expression of type " + currentType);
            }
        }

        statement.getExpression().accept(this);
        if (currentType != declaration.getType()) {
            fail("expression of type " + declaration.getType() + " for assignment",
                    "expression of type " + currentType);
        }
    }

    @Override
    public void visit(AssumeStatement statement) {
        statement.getCondition().accept(this);
        if (currentType != Type.BOOLEAN) {
            fail("boolean condition in assume statement", "expression of type " + currentType);
        }
    }

    @Override
    public void visit(ConditionalStatement statement) {
        statement.getCondition().accept(this);
        if (currentType != Type.BOOLEAN) {
            fail("boolean condition for if statement", "expression of type " + currentType);
        }

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
            statement.getExpression().accept(this);
            if (currentType != statement.getType()) {
                fail("expression of type " + statement.getType() + " for initialization",
                        "expression of type " + currentType);
            }
        }

        if (findDeclaration(statement.getId()) != null) {
            throw new TypeException("identifier \'" + statement.getId() + "\' declared twice");
        }

        frames.peek().add(new Declaration(statement));
    }

    @Override
    public void visit(Function function) {
        frames.push(new LinkedList<>(function.getParameters()));
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
        statement.getCondition().accept(this);
        if (currentType != Type.BOOLEAN) {
            fail("boolean condition for while statement", "expression of type " + currentType);
        }

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
        expression.getLeft().accept(this);
        if (currentType != Type.INTEGER) {
            fail("expression of type Int for addition", "expression of type " + currentType);
        }

        expression.getRight().accept(this);
        if (currentType != Type.INTEGER) {
            fail("expression of type Int for addition", "expression of type " + currentType);
        }

        currentType = Type.INTEGER;
    }

    @Override
    public void visit(IntegerLiteralExpression expression) {
        currentType = Type.INTEGER;
    }

    @Override
    public void visit(ModuloExpression expression) {
        expression.getLeft().accept(this);
        if (currentType != Type.INTEGER) {
            fail("expression of type Int for modulo", "expression of type " + currentType);
        }

        expression.getRight().accept(this);
        if (currentType != Type.INTEGER) {
            fail("expression of type Int for modulo", "expression of type " + currentType);
        }

        currentType = Type.INTEGER;
    }

    @Override
    public void visit(MultiplicationExpression expression) {
        expression.getLeft().accept(this);
        if (currentType != Type.INTEGER) {
            fail("expression of type Int for multiplication", "expression of type " + currentType);
        }

        expression.getRight().accept(this);
        if (currentType != Type.INTEGER) {
            fail("expression of type Int for multiplication", "expression of type " + currentType);
        }

        currentType = Type.INTEGER;
    }

    @Override
    public void visit(NegationExpression expression) {
        expression.getSubexpression().accept(this);
        if (currentType != Type.INTEGER) {
            fail("expression of type Int for negation", "expression of type " + currentType);
        }

        currentType = Type.INTEGER;
    }

    @Override
    public void visit(SubtractionExpression expression) {
        expression.getLeft().accept(this);
        if (currentType != Type.INTEGER) {
            fail("expression of type Int for subtraction", "expression of type " + currentType);
        }

        expression.getRight().accept(this);
        if (currentType != Type.INTEGER) {
            fail("expression of type Int for subtraction", "expression of type " + currentType);
        }

        currentType = Type.INTEGER;
    }

    @Override
    public void visit(VariableExpression expression) {
        Declaration declaration = findDeclaration(expression.getId());
        if (declaration == null) {
            throw new TypeException("\'" + expression.getId() +"\' is used but not declared");
        }

        currentType = declaration.getType();
    }

    ///////////////////////////////////////////////////////////////////
    // Boolean expressions
    //

    @Override
    public void visit(AndExpression expression) {
        expression.getLeft().accept(this);
        if (currentType != Type.BOOLEAN) {
            fail("expression of type Bool for logical and", "expression of type " + currentType);
        }

        expression.getRight().accept(this);
        if (currentType != Type.BOOLEAN) {
            fail("expression of type Bool for logical and", "expression of type " + currentType);
        }

        currentType = Type.BOOLEAN;
    }

    @Override
    public void visit(BooleanLiteralExpression expression) {
        currentType = Type.BOOLEAN;
    }

    @Override
    public void visit(EqualExpression expression) {
        expression.getLeft().accept(this);
        Type leftType = currentType;

        expression.getRight().accept(this);
        if (currentType != leftType) {
            fail("expression of type " + leftType, "expression of type " + currentType);
        }

        currentType = Type.BOOLEAN;
    }

    @Override
    public void visit(GreaterEqualExpression expression) {
        expression.getLeft().accept(this);
        Type leftType = currentType;

        expression.getRight().accept(this);
        if (currentType != leftType) {
            fail("expression of type " + leftType, "expression of type " + currentType);
        }

        currentType = Type.BOOLEAN;
    }

    @Override
    public void visit(GreaterThanExpression expression) {
        expression.getLeft().accept(this);
        Type leftType = currentType;

        expression.getRight().accept(this);
        if (currentType != leftType) {
            fail("expression of type " + leftType, "expression of type " + currentType);
        }

        currentType = Type.BOOLEAN;
    }

    @Override
    public void visit(ImplicationExpression expression) {
        expression.getLeft().accept(this);
        if (currentType != Type.BOOLEAN) {
            fail("expression of type Bool for logical implication", "expression of type " + currentType);
        }

        expression.getRight().accept(this);
        if (currentType != Type.BOOLEAN) {
            fail("expression of type Bool for logical implication", "expression of type " + currentType);
        }

        currentType = Type.BOOLEAN;
    }

    @Override
    public void visit(LessEqualExpression expression) {
        expression.getLeft().accept(this);
        Type leftType = currentType;

        expression.getRight().accept(this);
        if (currentType != leftType) {
            fail("expression of type " + leftType, "expression of type " + currentType);
        }

        currentType = Type.BOOLEAN;
    }

    @Override
    public void visit(LessThanExpression expression) {
        expression.getLeft().accept(this);
        Type leftType = currentType;

        expression.getRight().accept(this);
        if (currentType != leftType) {
            fail("expression of type " + leftType, "expression of type " + currentType);
        }

        currentType = Type.BOOLEAN;
    }

    @Override
    public void visit(NotEqualExpression expression) {
        expression.getLeft().accept(this);
        Type leftType = currentType;

        expression.getRight().accept(this);
        if (currentType != leftType) {
            fail("expression of type " + leftType, "expression of type " + currentType);
        }

        currentType = Type.BOOLEAN;
    }

    @Override
    public void visit(NotExpression expression) {
        expression.getSubexpression().accept(this);
        if (currentType != Type.BOOLEAN) {
            fail("expression of type Bool for logical negation", "expression of type " + currentType);
        }

        currentType = Type.BOOLEAN;
    }

    @Override
    public void visit(OrExpression expression) {
        expression.getLeft().accept(this);
        if (currentType != Type.BOOLEAN) {
            fail("expression of type Bool for logical or", "expression of type " + currentType);
        }

        expression.getRight().accept(this);
        if (currentType != Type.BOOLEAN) {
            fail("expression of type Bool for logical or", "expression of type " + currentType);
        }

        currentType = Type.BOOLEAN;
    }

    @Override
    public void visit(RelationExpression expression) {
        // TODO: check arguments
    }
}
