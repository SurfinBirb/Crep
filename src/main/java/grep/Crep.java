package grep;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SurfinBirb on 23.03.2017.
 */
public class Crep {

    private String keyword;
    private  boolean invert;
    private  boolean ignore;
    private  boolean regex;
    private Pattern pattern;

    public void runCrep(String word, String path, boolean v, boolean i, boolean r) throws IOException {
        this.keyword = word;
        this.ignore = i;
        this.invert = v;
        this.regex = r;
        this.compilePattern();
        Files.lines(Paths.get(path).toAbsolutePath(), StandardCharsets.UTF_8).forEach(this::crep);
    }

    private void compilePattern() {

    }

    private void crep(String s){

    }

}
