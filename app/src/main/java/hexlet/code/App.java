package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    private String filepath2;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @Override
    public Integer call() {
        try {
            var diff = Differ.generate(filepath1, filepath2, format);
            System.out.println(diff);
        } catch (Exception e) {
            System.out.println("Error");
            return 1;
        }
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
