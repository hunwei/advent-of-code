package advent.code.day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Part2 {

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
                    // e.g 5-9 g: ggccggmgn = true as 5th character match 'g' and 9th character is not
                    boolean matchPosition1 = Character.toString(p[2].charAt(min - 1)).equals(p[1])
                            && !Character.toString(p[2].charAt(max - 1)).equals(p[1]);
                    // e.g 3-4 r: blqrrrr = true as 3rd character is not 'r' but 4th character is
                    boolean matchPosition2 = !Character.toString(p[2].charAt(min - 1)).equals(p[1])
                            && Character.toString(p[2].charAt(max - 1)).equals(p[1]);
                    return matchPosition1 || matchPosition2;
                }).count();
        System.out.println(valid);
    }
}
