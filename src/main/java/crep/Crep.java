package crep;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SurfinBirb on 23.03.2017.
 */
 class Crep {

    private String keyword;
    private  boolean invert=false;
    private  boolean ignore=false;
    private  boolean regex=false;
    private Pattern pattern;

    void runCrep(String word, String path, boolean v, boolean i, boolean r) throws IOException {
        this.keyword = word;
        this.ignore = i;
        this.invert = v;
        this.regex = r;
        this.compilePattern();
        Files.lines(Paths.get(path).toAbsolutePath(), StandardCharsets.UTF_8).forEach(this::crep);
    }

    private void compilePattern() {
        if(ignore && invert && regex) pattern = Pattern.compile( "^((?!" + keyword + ").)*$", Pattern.CASE_INSENSITIVE );
        if(ignore && invert && !regex) pattern = Pattern.compile( "^((?!" + keyword + ").)*$", Pattern.CASE_INSENSITIVE );
        if(ignore &&! invert && !regex) pattern = Pattern.compile( ".*" + keyword + ".*", Pattern.CASE_INSENSITIVE );
        if(ignore &&! invert && regex) pattern = Pattern.compile( ".*" + keyword + ".*", Pattern.CASE_INSENSITIVE );
        if(!ignore && !invert && regex) pattern = Pattern.compile( ".*" + keyword + ".*" );
        if(!ignore && invert && regex) pattern = Pattern.compile( "^((?!" + keyword + ").)*$" );
        if(!ignore && invert && !regex) pattern = Pattern.compile( "^((?!" + keyword + ").)*$" );
        if(!ignore &&! invert && !regex) pattern = Pattern.compile( ".*" + keyword + ".*" );
    }

    private void crep(String s) {
            Matcher matcher = this.pattern.matcher(s);
            if (matcher.matches()) System.out.println(s);
        }
}
