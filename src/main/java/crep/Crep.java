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

    private static String keyword;
    private static boolean invert = false;
    private static boolean ignore = false;
    private static boolean regex = false;
    private static Pattern pattern;

    private static void compilePattern() {
        if(ignore && invert && regex) pattern = Pattern.compile( "^((?!" + keyword + ").)*$", Pattern.CASE_INSENSITIVE );
        if(ignore && invert && !regex) pattern = Pattern.compile( "^((?!\\Q" + keyword + "\\E).)*$", Pattern.CASE_INSENSITIVE );
        if(ignore && !invert && !regex) pattern = Pattern.compile( ".*\\Q" + keyword + "\\E.*", Pattern.CASE_INSENSITIVE );
        if(ignore && !invert && regex) pattern = Pattern.compile( ".*" + keyword + ".*", Pattern.CASE_INSENSITIVE );
        if(!ignore && !invert && regex) pattern = Pattern.compile( ".*" + keyword + ".*" );
        if(!ignore && invert && regex) pattern = Pattern.compile( "^((?!" + keyword + ").)*$" );
        if(!ignore && invert && !regex) pattern = Pattern.compile( "^((?!\\Q" + keyword + "\\E).)*$" );
        if(!ignore && !invert && !regex) pattern = Pattern.compile( ".*\\Q" + keyword + "\\E.*" );
    }

    static void runCrep(String word, String path, boolean v, boolean i, boolean r) throws IOException {
        keyword = word;
        ignore = i;
        invert = v;
        regex = r;
        compilePattern();
        Files.lines(Paths.get(path).toAbsolutePath(), StandardCharsets.UTF_8).forEach(
                s -> {
                    if(!s.equals("")) {
                        Matcher matcher = pattern.matcher(s);
                        if (matcher.matches()) System.out.println(s);
                    }
                }
        );
    }
}
