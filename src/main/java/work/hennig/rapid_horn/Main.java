package work.hennig.rapid_horn;

import work.hennig.rapid_horn.analysis.TypeChecker;
import work.hennig.rapid_horn.cfg.CFG;
import work.hennig.rapid_horn.parser.Parser;
import work.hennig.rapid_horn.parser.ParserException;
import work.hennig.rapid_horn.rapid.Program;
import work.hennig.rapid_horn.transformations.CFG2Horn;
import work.hennig.rapid_horn.transformations.Rapid2CFG;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            usage();
            System.exit(0);
        }
        for (int i = 0; i < args.length; i++) {
            String input = "";
            try {
                input = Files.readString(Path.of(args[i]));
            } catch (IOException e) {
                System.err.println("ERROR: cannot open " + e.getMessage());
                System.exit(1);
            }

            Program program = null;
            try {
                Parser parser = new Parser(input);
                program = parser.parseProgram();
            } catch (ParserException e) {
                System.err.println("ERROR: " + e.getMessage());
                System.exit(1);
            }

            if (!TypeChecker.check(program)) {
                System.err.println("ERROR: type check failed");
            }
            CFG cfg = Rapid2CFG.transform(program);

            CFG2Horn.transform(cfg);
        }
    }

    public static void usage() {
        System.err.println("usage: java -jar RapidHorn.java <list of input files>");
    }
}
