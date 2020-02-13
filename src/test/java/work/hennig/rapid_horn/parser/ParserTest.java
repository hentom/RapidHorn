package work.hennig.rapid_horn.parser;

import org.junit.jupiter.api.Test;
import work.hennig.rapid_horn.rapid.*;
import work.hennig.rapid_horn.expression.*;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    public void parseProgram_Parameters_ArithmeticExpressions() {
        Parser parser = new Parser("func function(const Int a, Int[] b) { Int result = -(a + b[0]) * b[a]; skip; }");
        Program program = parser.parseProgram();

        assertEquals(1, program.getFunctions().size());

        Function function = program.getFunctions().get(0);
        assertEquals("function", function.getId());
        assertEquals(2, function.getParameters().size());
        assertEquals(2, function.getStatements().size());

        Declaration aPar = function.getParameters().get(0);
        assertTrue(aPar.isConstant());
        assertEquals(Type.INTEGER, aPar.getType());
        assertFalse(aPar.isArray());
        assertEquals("a", aPar.getId());

        Declaration bPar = function.getParameters().get(1);
        assertFalse(bPar.isConstant());
        assertEquals(Type.INTEGER, bPar.getType());
        assertTrue(bPar.isArray());
        assertEquals("b", bPar.getId());

        DeclarationStatement declaration = (DeclarationStatement) function.getStatements().get(0);
        assertFalse(declaration.isConstant());
        assertEquals(Type.INTEGER, declaration.getType());
        assertFalse(declaration.isArray());
        assertEquals("result", declaration.getId());
        assertTrue(declaration.hasExpression());

        MultiplicationExpression multiplication = (MultiplicationExpression) declaration.getExpression();
        NegationExpression negation = (NegationExpression) multiplication.getLeft();
        AdditionExpression addition = (AdditionExpression) negation.getSubexpression();
        VariableExpression b2Var = (VariableExpression) multiplication.getRight();
        assertEquals("b", b2Var.getId());
        assertTrue(b2Var.isArray());

        VariableExpression a1Var = (VariableExpression) addition.getLeft();
        assertEquals("a", a1Var.getId());
        assertFalse(a1Var.isArray());

        VariableExpression b1Var = (VariableExpression) addition.getRight();
        assertEquals("b", b1Var.getId());
        assertTrue(b1Var.isArray());

        IntegerLiteralExpression zero = (IntegerLiteralExpression) b1Var.getIndex();
        assertEquals(0, zero.getValue());

        VariableExpression a2Var = (VariableExpression) b2Var.getIndex();
        assertEquals("a", a2Var.getId());
        assertFalse(a2Var.isArray());

        assertTrue(function.getStatements().get(1) instanceof SkipStatement);
    }
}