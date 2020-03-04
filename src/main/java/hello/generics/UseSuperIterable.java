package hello.generics;

import java.util.Arrays;
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


    }

//    public static <T> void showAll(SuperIterable<T> target) {
//        for (T s : target) {
//            System.out.println(">> " + s);
//        }
//    }
}
