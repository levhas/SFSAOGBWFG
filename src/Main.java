import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> sanat;

        String[] grid = {   "e", "l", "u", "l",
                            "k", "o", "u", "r",
                            "i", "i", "u", "e",
                            "o", "p", "l", "u"};

        try (var t =Files.lines(Path.of("kotus-sanalista_v1/kotus-sanalista_v1.xml"))){
            sanat = t
                    .filter(sana -> sana.length() < 10 && sana.chars().allMatch(Character::isLetter))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

       /* Arrays.stream(grid).forEachOrdered(k -> kirjaimet.add(new Letter(k)));
        kirjaimet.forEach(kirjain -> kirjain.setNeighbours(4));
*/
        sanat = new ArrayList<>(sanat);
        sanat = sanat.stream().sorted().collect(Collectors.toList());
        Solver solver = new Solver(grid,sanat);
        var t = System.nanoTime();
        var solvedWords = solver.solve();
        System.out.println("found " + solvedWords.size() + "words in " + (TimeUnit.SECONDS.convert(( System.nanoTime()- t), TimeUnit.NANOSECONDS)) +" seconds");
        solvedWords.forEach(System.out::println);


    }


}