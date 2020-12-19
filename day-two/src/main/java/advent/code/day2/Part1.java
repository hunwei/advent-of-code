package advent.code.day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Part1 {

    public static void main(String... args) throws IOException, URISyntaxException {
        int valid = (int) Files.readAllLines(Paths.get(Thread.currentThread().getContextClassLoader()
                .getResource("input.txt").toURI()))
                .stream()
                /*
                 * replace : to empty and split by space. E.g "5-9 g: ggccggmgn"
                 * p[0] = 5-9
                 * p[1] = g
                 * p[2] = ggccggmgn
                 */
                .map(p -> p.replace(":", "").split(" "))
                .filter(p -> {
                    /* get min & max character to match */
                    int min = Integer.parseInt(p[0].split("-")[0]);
                    int max = Integer.parseInt(p[0].split("-")[1]);
                    /* preliminary validation to skip the line where min/max > string length */
                    if (min > p[2].length() || max > p[2].length()) {
                        return false;
                    }
                    /* Get number of character match p[1](g) in string */
                    int count = (int) p[2].chars()
                            .mapToObj(c -> (char) c)
                            .filter(c -> c.toString().equals(p[1]))
                            .count();
                    return count >= min && count <= max;
                }).count();
        System.out.println(valid);
    }
}
