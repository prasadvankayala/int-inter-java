package hello.streams;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concordance {
    public static void main(String[] args) throws Throwable {
        final Pattern WORD_BOUNDARY = Pattern.compile("\\W+");
        try (Stream<String> stream = Files.lines(Paths.get("PrideAndPrejudice.txt"))) {
            stream
                    .flatMap(WORD_BOUNDARY::splitAsStream)
                    .filter(s -> !s.isEmpty())
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(x -> x))
                    .forEach((k, v) -> System.out.println("Word " + k + " occurs " + v.size() + " times"));
//                    .forEach(System.out::println);
        }
    }
}
