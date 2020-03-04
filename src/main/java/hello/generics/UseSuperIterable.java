package hello.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

//interface User<T> {
//    void accept(T t);
//}


class Printer implements Consumer<String> {

    @Override
    public void accept(String s) {
        System.out.println(">>>> " + s);
    }
}

class ShortCriterion implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return s.length() < 6;
    }
}

public class UseSuperIterable {
    public static void main(String[] args) {
        SuperIterable<String> sis = new SuperIterable<>(Arrays.asList("Fred", "Jim", "Sheila"));

        sis.forEvery(new Printer());
        System.out.println("-------------------------------");
        sis
                .filter(new ShortCriterion())
                .forEvery(new Printer());


        System.out.println("Version 2 ---------------------------");
        sis
                .filter(new ShortCriterion())
                .forEvery(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println("More Output " + s);
                    }
                });

        System.out.println("Version 3 ---------------------------");
        sis
                .filter(new ShortCriterion())
                .forEvery(/*new Consumer<String>() {
                    @Override
                    public void accept*/(String s) -> {
                        System.out.println("More Output " + s);
                    }
                /*}*/);

        System.out.println("Version 4 ---------------------------");
        sis
                .filter(new ShortCriterion())
//                .forEvery((/*String */s) -> {
//                .forEvery(s -> {
//                        System.out.println("More Output " + s);
//                    });
                .forEvery(s -> System.out.println("More Output " + s));

        System.out.println("Lab 1 ---------------------------");
        sis
                .filter(new ShortCriterion())
                .forEvery(s -> System.out.println("different " + s));

        System.out.println("Lab 2 ---------------------------");
        List<String> results = new ArrayList<>();
        sis
                .filter(new ShortCriterion())
                .forEvery(s -> results.add(s)); // DON'T DO "EXTERNAL" MUTATION!!! (stylistic)

        results.forEach(s -> System.out.println(">>> " + s));

        System.out.println("Lab 3 ---------------------------");
        sis
                .filter((String s) -> { return s.length() < 6; })
                .filter((s) -> { return s.length() < 6; })
                .filter(s -> { return s.length() < 6; })
                .filter(s -> s.length() < 6)
                .forEvery(s -> System.out.println("short " + s));

        System.out.println("Lab 4 ---------------------------");
        sis
                .filter(s -> s.length() > 3)
                .forEach(s -> System.out.println("long " + s));

        System.out.println("Lab 5 ---------------------------");
        List<String> names = new ArrayList<>(Arrays.asList("Fred", "Jim", "Sheila"));
        names.sort((String s1, String s2) -> s2.compareTo(s1));
        names.forEach(s -> System.out.println("> " + s));

        System.out.println("Lab 5 ---------------------------");
        names.sort((s1, s2) -> s1.length() - s2.length());
        names.forEach(s -> System.out.println("> " + s));

        System.out.println("mapping ---------------------------");
        sis
                .map(s -> s.toUpperCase())
                .forEach(s -> System.out.println(">" + s));

        System.out.println("mapping ---------------------------");
        sis
                .map(s -> s.length())
                .forEach(s -> System.out.println(">> " + s));

        System.out.println("mapping ---------------------------");
        sis
                .filter(s -> s.length()>3)
                .map(s -> s.length())
                .forEach(s -> System.out.println(">> " + s));



    }

//    public static <T> void showAll(SuperIterable<T> target) {
//        for (T s : target) {
//            System.out.println(">> " + s);
//        }
//    }
}
