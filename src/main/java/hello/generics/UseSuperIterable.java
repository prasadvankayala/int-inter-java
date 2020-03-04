package hello.generics;

import java.util.Arrays;

interface User<T> {
    void accept(T t);
}

class Printer implements User<String> {

    @Override
    public void accept(String s) {
        System.out.println(">>>> " + s);
    }
}

class ShortCriterion implements Criterion<String> {

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
    }

//    public static <T> void showAll(SuperIterable<T> target) {
//        for (T s : target) {
//            System.out.println(">> " + s);
//        }
//    }
}
