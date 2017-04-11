package crep;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.nio.file.Paths;

import static crep.Crep.*;

/**
 * Created by SurfinBirb on 22.03.2017.
 */
public class Console {

    @Argument(required = true, metaVar = "Command", usage = "Command")
    public String command;

    @Option(name = "-v", metaVar = "Invert", usage = "Инвертировать условие фильтрации")
    private boolean invert;

    @Option(name = "-i", metaVar = "Ignore", usage = "Игнорировать регистр")
    private boolean ignore;

    @Option(name = "-r", metaVar = "Regex", usage = "Использовать слово как регулярное выражение")
    private boolean regex;

    @Argument(metaVar = "word", index = 1, usage = "Key word or regular expression")
    private String word = null;

    @Argument(metaVar = "Path", index = 2, usage = "File path")
    private String path = null;

    public static void main(String[] args) throws CmdLineException, IOException {
        new Console().crepLaunch(args);
    }

    private void crepLaunch(String[] args) throws CmdLineException, IOException {
        CmdLineParser parser = new CmdLineParser(this);
        parser.parseArgument(args);
        if (command.equals("help")) printHelp();
        else {
            if (command.equals("crep") && path != null && word != null) {
                if (Paths.get(path).toAbsolutePath().toFile().exists()) runCrep(word, path, invert, ignore, regex);
                else {
                    System.out.println(Paths.get(path).toAbsolutePath() + " - No such file or directory");
                    printHelp();
                }
            } else {
                System.out.println("Bad command line args!");
                printHelp();
            }
        }
    }

    private void printHelp(){
        System.out.println("\nCommand line: crep -v -i -r word filename.txt");
        System.out.println("crep - Command");
        System.out.println(" -v - Инвертировать условие фильтрации");
        System.out.println(" -i - Игнорировать регистр");
        System.out.println(" -r - Использовать слово как регулярное выражение");
        System.out.println(" word - Key word or regular expression");
        System.out.println(" filename.txt - File path");
    }
}
