package work.hennig.rapid_horn.parser;

import work.hennig.rapid_horn.ast.Function;
import work.hennig.rapid_horn.ast.Program;
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
        Token token = scanner.nextToken();
        if (token.getType() != TokenType.KEYWORD_FUNC) {
            fail("keyword \'func\'", token.getContent());
        }

        token = scanner.nextToken();
        if (token.getType() != TokenType.IDENTIFIER) {
            fail("function identifier", token.getContent());
        }
        String id = token.getContent();

        token = scanner.nextToken();
        if (token.getType() != TokenType.SYM_LEFT_PARENTHESIS) {
            fail("\'(\'", token.getContent());
        }

        // TODO

        throw new UnsupportedOperationException();
    }

    private void fail(String expected, String actual) {
        System.err.println("ERROR: (parser) expected " + expected + " instead of \'" + actual + "\'");
        System.exit(1);
    }
}
