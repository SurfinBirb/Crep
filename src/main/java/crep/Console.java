package crep;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

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

    @Argument(required = true, metaVar = "word", index = 1, usage = "Key word or regular expression")
    private String word;

    @Argument(required = true, metaVar = "Path", index = 2, usage = "File path")
    private String path;

    public static void main(String[] args) throws CmdLineException, IOException {
        new Console().crepLaunch(args);
    }

    private void crepLaunch(String[] args) throws CmdLineException, IOException {
        CmdLineParser parser = new CmdLineParser(this);
        Crep crepObj = new Crep();
        parser.parseArgument(args);
        crepObj.runCrep(word, path, invert, ignore, regex);
    }
}
