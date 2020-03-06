package hello.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class SelectingText {
    public static <T> List<T> filter(Iterable<T> in, Predicate<? super T> pred) {
        List<T> out = new ArrayList<>();
        in.forEach(item -> {
            if (pred.test(item)) out.add(item);
        });
        return out;
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Fred", "Jim", "Sheila");
        Predicate<String> ps = s -> s.length() > 3;

        System.out.println("Long strings " + filter(names, ps));

        List<StringBuilder> names2 =
                Arrays.asList(new StringBuilder("Fred"), new StringBuilder("Jim"), new StringBuilder("Sheila"));
        Predicate<CharSequence> ps2 = s -> s.length() > 3;

        System.out.println("Long sbs " + filter(names2, ps2));
        System.out.println("Long strings " + filter(names, ps2));
    }
}
