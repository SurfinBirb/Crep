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
        if(ignore && invert && !regex) pattern = Pattern.compile( "^((?!\\Q" + keyword + "\\E).)*$", Pattern.CASE_INSENSITIVE );
        if(ignore && !invert && !regex) pattern = Pattern.compile( ".*\\Q" + keyword + "\\E.*", Pattern.CASE_INSENSITIVE );
        if(ignore && !invert && regex) pattern = Pattern.compile( ".*" + keyword + ".*", Pattern.CASE_INSENSITIVE );
        if(!ignore && !invert && regex) pattern = Pattern.compile( ".*" + keyword + ".*" );
        if(!ignore && invert && regex) pattern = Pattern.compile( "^((?!" + keyword + ").)*$" );
        if(!ignore && invert && !regex) pattern = Pattern.compile( "^((?!\\Q" + keyword + "\\E).)*$" );
        if(!ignore && !invert && !regex) pattern = Pattern.compile( ".*\\Q" + keyword + "\\E.*" );
    }

    private void crep(String s) {
        if(!s.equals("")) {
            Matcher matcher = this.pattern.matcher(s);
            if (matcher.matches()) System.out.println(s);
        }
    }

    @Override
    public String toString() {
        return "Crep{ " +
                "keyword = '" + keyword.toString() + '\'' +
                ", invert = " + invert +
                ", ignore = " + ignore +
                ", regex = " + regex +
                ", pattern = " + pattern.toString() +
                " }";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        Crep downcastedOther = (Crep) other;

        if (invert != downcastedOther.invert) return false;
        if (ignore != downcastedOther.ignore) return false;
        if (regex != downcastedOther.regex) return false;
        if (!keyword.equals(downcastedOther.keyword)) return false;
        if (!pattern.equals(downcastedOther.pattern)) return false;
        else return true;
    }

    @Override
    public int hashCode() {
        int result = keyword.hashCode();
        result = 31 * result + (invert ? 1 : 0);
        result = 31 * result + (ignore ? 1 : 0);
        result = 31 * result + (regex ? 1 : 0);
        result = 31 * result + pattern.hashCode();
        return result;
    }
}
