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
            String input = "";
            try {
                input = Files.readString(Path.of(args[i]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parser parser = new Parser(input);
            Program program = parser.parseProgram();
            if (!TypeChecker.check(program)) {
                System.err.println("ERROR: type check failed");
            }
        }
    }
}
