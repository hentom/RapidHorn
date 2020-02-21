# RapidHorn
Verification framework for while programs based on constrained horn clauses

## About
RapidHorn is a horn clause encoder for the specification language used by [Rapid](https://github.com/gleiss/rapid). This language is a while programming language mostly using the syntax of Java/C. Example programs can be found in the [`examples`](https://github.com/hentom/RapidHorn/tree/master/examples) folder. The generated output encodings specifically target [Z3](https://github.com/Z3Prover/z3) and in particular its fixed-point engine Spacer.

RapidHorn is not intended for commercial/professional usage (as one can already tell from the supported input language), but for benchmarking and general comparison to other verification approaches.

## Usage
It is recommended to build the current version from the provided source code as I will only occasionally update the [releases](https://github.com/hentom/RapidHorn/releases). After packaging the compilation output into a jar-package with the main class `work.hennig.rapid_horn.Main` it can be executed by calling `java -jar RapidHorn.java <input file>`. The given input file is then parsed, analysed and encoded in constrained horn clauses using an extension of the [SMT-LIB](http://smtlib.cs.uiowa.edu) syntax. The used extension is partially explained [here](https://rise4fun.com/z3/tutorialcontent/fixedpoints#h24).
