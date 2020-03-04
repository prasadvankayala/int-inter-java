package hello.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Ex1 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Fred", "Jim", "Sheila");
        Stream<String> ss = names.stream();
        String res = ss.map(s -> s.toUpperCase())
                .reduce("", (a, b) -> a + ", " + b);
        System.out.println(res);

        names.stream().forEach(s -> System.out.println(s));
    }
}
