package work.hennig.rapid_horn;

import work.hennig.rapid_horn.analysis.TypeChecker;
import work.hennig.rapid_horn.parser.Parser;
import work.hennig.rapid_horn.rapid.Program;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            Path path = Path.of(args[i]);
            try {
                String input = Files.readString(Path.of(args[i]));
                Parser parser = new Parser(input);
                Program program = parser.parseProgram();
                System.out.println("type check: " + TypeChecker.check(program));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
