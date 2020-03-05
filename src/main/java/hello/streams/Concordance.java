package hello.streams;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Concordance {
    public static void main(String[] args) throws Throwable {
        Comparator<Map.Entry<String, Long>> entryByValue = Map.Entry.comparingByValue();
        Comparator<Map.Entry<String, Long>> descendingEntryByValue = entryByValue.reversed();
        final Pattern WORD_BOUNDARY = Pattern.compile("\\W+");
        try (Stream<String> stream = Files.lines(Paths.get("PrideAndPrejudice.txt"))) {
            stream
                    .flatMap(WORD_BOUNDARY::splitAsStream)
                    .filter(s -> !s.isEmpty())
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(/*x -> x*/
                            Function.identity(), Collectors.counting()))
                    .entrySet().stream()
//                    .sorted((e1, e2)-> Long.compare(e2.getValue(), e1.getValue()))
//                    .sorted(descendingEntryByValue)
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .limit(200)
                    .forEach(e -> System.out.println("Word " + e.getKey() + " occurs " + e.getValue() + " times"))
            ;
        }
    }
}
