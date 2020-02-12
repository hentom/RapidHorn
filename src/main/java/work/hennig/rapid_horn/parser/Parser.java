package work.hennig.rapid_horn.parser;

import work.hennig.rapid_horn.ast.*;
import work.hennig.rapid_horn.ast.arithmetic_expression.*;
import work.hennig.rapid_horn.ast.boolean_expression.BooleanExpression;
import work.hennig.rapid_horn.ast.boolean_expression.RelationalExpression;
import work.hennig.rapid_horn.parser.scanner.Scanner;
import work.hennig.rapid_horn.parser.scanner.Token;
import work.hennig.rapid_horn.parser.scanner.TokenType;

import java.util.LinkedList;
import java.util.List;

public class Parser {

    private Scanner scanner;

    public Parser(String input) {
        this.scanner = new Scanner(input);
    }

    public Program parseProgram() {
        List<Function> functions = new LinkedList<>();
        Token token = scanner.nextToken();
        while (token.getType() != TokenType.EOF) {
            scanner.pushToken(token);
            functions.add(parseFunction());
            token = scanner.nextToken();
        }
        return new Program(functions);
    }

    private Function parseFunction() {
        expect(TokenType.KEY_FUNC);

        Token token = scanner.nextToken();
        if (token.getType() != TokenType.IDENTIFIER) {
            fail("function identifier", token.getContent());
        }
        String id = token.getContent();

        expect(TokenType.SYM_LEFT_PARENTHESIS);
        List<Declaration> parameters = new LinkedList<>();
        token = scanner.nextToken();
        while (token.getType() == TokenType.KEY_CONST ||
                token.getType() == TokenType.TYPE_BOOL ||
                token.getType() == TokenType.TYPE_INT) {
            scanner.pushToken(token);
            parameters.add(parseDeclaration());

            token = scanner.nextToken();
            if (token.getType() == TokenType.SYM_COMMA) {
                token = scanner.nextToken();
            }
        }
        scanner.pushToken(token);
        expect(TokenType.SYM_RIGHT_PARENTHESIS);

        List<Statement> statements = parseBlock();

        return new Function(id, parameters, statements);
    }

    private List<Statement> parseBlock() {
        expect(TokenType.SYM_LEFT_CURLY_BRACKET);

        List<Statement> block = new LinkedList<>();
        Token token = scanner.nextToken();
        while (token.getType() != TokenType.SYM_RIGHT_CURLY_BRACKET) {
            scanner.pushToken(token);
            block.add(parseStatement());
            token = scanner.nextToken();
        }

        return block;
    }

    private Declaration parseDeclaration() {
        boolean isConst = false;
        Token token = scanner.nextToken();
        if (token.getType() == TokenType.KEY_CONST) {
            isConst = true;
            token = scanner.nextToken();
        }

        Type type = Type.INTEGER;
        if (token.getType() == TokenType.TYPE_INT) {
            type = Type.INTEGER;
        } else if (token.getType() == TokenType.TYPE_BOOL) {
            type = Type.BOOLEAN;
        } else {
            fail("data type", token.getContent());
        }

        boolean isArray = false;
        token = scanner.nextToken();
        if (token.getType() == TokenType.SYM_LEFT_SQUARE_BRACKET) {
            expect(TokenType.SYM_RIGHT_SQUARE_BRACKET);
            isArray = true;
        } else {
            scanner.pushToken(token);
        }

        String id = "";
        token = scanner.nextToken();
        if (token.getType() == TokenType.IDENTIFIER) {
            id = token.getContent();
        } else {
            fail("variable identifier", token.getContent());
        }

        return new Declaration(isConst, type, id, isArray);
    }

    private Statement parseStatement() {
        Token token = scanner.nextToken();
        scanner.pushToken(token);
        switch (token.getType()) {
            case KEY_CONST: case TYPE_INT: case TYPE_BOOL:
                return parseDeclarationStatement();
            case IDENTIFIER:
                return parseAssignmentStatement();
            case KEY_IF:
                return parseConditionalStatement();
            case KEY_WHILE:
                return parseWhileStatement();
            case KEY_SKIP:
                expect(TokenType.KEY_SKIP);
                expect(TokenType.SYM_SEMICOLON);
                return new SkipStatement();
        }
        fail("beginning of statement", token.getContent());
        return null; // never reached, as fail throws exception
    }

    private DeclarationStatement parseDeclarationStatement() {
        Declaration declaration = parseDeclaration();

        Token token = scanner.nextToken();
        if (token.getType() == TokenType.OP_ASSIGN) {
            ArithmeticExpression expression = parseArithmeticExpression();
            expect(TokenType.SYM_SEMICOLON);
            return new DeclarationStatement(declaration, expression);
        } else {
            scanner.pushToken(token);
            expect(TokenType.SYM_SEMICOLON);
            return new DeclarationStatement(declaration);
        }
    }

    private AssignmentStatement parseAssignmentStatement() {
        String id = "";
        Token token = scanner.nextToken();
        if (token.getType() == TokenType.IDENTIFIER) {
            id = token.getContent();
        } else {
            fail("variable identifier", token.getContent());
        }

        expect(TokenType.OP_ASSIGN);

        ArithmeticExpression expression = parseArithmeticExpression();

        expect(TokenType.SYM_SEMICOLON);

        return new AssignmentStatement(id, expression);
    }

    private ConditionalStatement parseConditionalStatement() {
        expect(TokenType.KEY_IF);

        expect(TokenType.SYM_LEFT_PARENTHESIS);
        BooleanExpression condition = parseBooleanExpression();
        expect(TokenType.SYM_RIGHT_PARENTHESIS);

        List<Statement> ifBlock = parseBlock();

        Token token = scanner.nextToken();
        if (token.getType() != TokenType.KEY_ELSE) {
            scanner.pushToken(token);
            return new ConditionalStatement(condition, ifBlock);
        }

        List<Statement> elseBlock = parseBlock();

        return new ConditionalStatement(condition, ifBlock, elseBlock);
    }

    private WhileStatement parseWhileStatement() {
        expect(TokenType.KEY_WHILE);

        expect(TokenType.SYM_LEFT_PARENTHESIS);
        BooleanExpression condition = parseBooleanExpression();
        expect(TokenType.SYM_RIGHT_PARENTHESIS);

        List<Statement> block = parseBlock();

        return new WhileStatement(condition, block);
    }

    private ArithmeticExpression parseArithmeticExpression() {
        ArithmeticExpression left = parseArithmeticTerm();

        Token token = scanner.nextToken();
        if (token.getType() == TokenType.OP_PLUS) {
            ArithmeticExpression right = parseArithmeticExpression();
            return new AdditionExpression(left, right);
        } else if (token.getType() == TokenType.OP_MINUS) {
            ArithmeticExpression right = parseArithmeticExpression();
            return new SubtractionExpression(left, right);
        } else {
            scanner.pushToken(token);
            return left;
        }
    }

    private ArithmeticExpression parseArithmeticTerm() {
        ArithmeticExpression left = parseArithmeticFactor();

        Token token = scanner.nextToken();
        if (token.getType() == TokenType.OP_MULTIPLICATION) {
            ArithmeticExpression right = parseArithmeticTerm();
            return new MultiplicationExpression(left, right);
        } else if (token.getType() == TokenType.OP_MODULO) {
            ArithmeticExpression right = parseArithmeticTerm();
            return new ModuloExpression(left, right);
        } else {
            scanner.pushToken(token);
            return left;
        }
    }

    private ArithmeticExpression parseArithmeticFactor() {
        Token token = scanner.nextToken();
        String content = token.getContent();
        switch (token.getType()) {
            case LIT_INT:
                return new LiteralExpression(Integer.parseInt(content));
            case IDENTIFIER:
                token = scanner.nextToken();
                if (token.getType() == TokenType.SYM_LEFT_SQUARE_BRACKET) {
                    ArithmeticExpression index = parseArithmeticExpression();
                    expect(TokenType.SYM_RIGHT_SQUARE_BRACKET);
                    return new VariableExpression(content, index);
                } else {
                    scanner.pushToken(token);
                    return new VariableExpression(content);
                }
            case OP_MINUS:
                return new NegationExpression(parseArithmeticFactor());
            case SYM_LEFT_PARENTHESIS: {
                ArithmeticExpression subexpression = parseArithmeticExpression();
                expect(TokenType.SYM_RIGHT_PARENTHESIS);
                return subexpression;
            }
        }
        throw new UnsupportedOperationException();
    }

    private BooleanExpression parseBooleanExpression() {
        throw new UnsupportedOperationException();
    }

    private RelationalExpression parseRelationalExpression() {
        throw new UnsupportedOperationException();
    }

    private void expect(TokenType type) {
        Token token = scanner.nextToken();
        if (token.getType() != type) {
            fail(type.toString(), token.getContent());
        }
    }

    private void fail(String expected, String actual) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("expected " + expected + " instead of \'" + actual + "\'");
    }
}
