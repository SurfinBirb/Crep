package grep;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by SurfinBirb on 22.03.2017.
 */
public class Grep {

    public void grep(String path) throws IOException {
        Files.lines(Paths.get(path).toAbsolutePath(), StandardCharsets.UTF_8).forEach(this::cat);
    }

    private void cat(String s) {
        System.out.println(s);
    }

}
