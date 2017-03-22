package grep;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

/**
 * Created by SurfinBirb on 22.03.2017.
 */
public class Console {

    @Option(name = "-v", metaVar = "Invert", usage = "Инвертировать условие фильтрации")
    public boolean invert;

    @Option(name = "-i", metaVar = "Ignore", usage = "Игнорировать регистр")
    public boolean ignore;

    @Option(name = "-r", metaVar = "Regex", usage = "Использовать слово как регулярное выражение")
    public boolean regex;

    @Argument(required = true, metaVar = "word", usage = "Key word or regular expression")
    public String word;

    @Argument(required = true, metaVar = "Path", index = 1, usage = "File path")
    public String path;

    public static void main(String[] args) throws CmdLineException, IOException {
        new Console().grepLaunch(args);
    }

    private void grepLaunch(String[] args) throws CmdLineException, IOException {
        CmdLineParser parser = new CmdLineParser(this);
        Grep analysator = new Grep();
        parser.parseArgument(args);
        analysator.grep(path);
    }
}
