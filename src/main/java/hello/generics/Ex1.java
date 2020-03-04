package hello.generics;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ex1 {
    public static void breakAList(List l) {
        l.add(0, LocalDate.now());
    }
    public static void main(String[] args) {
        List<String> l = new ArrayList<>(Arrays.asList("Fred"));
//        l = Collections.checkedList(l, String.class);
        l.add("Fred");
        l.add("Jim");
//        l.add(LocalDate.now());

//        breakAList(l);

        System.out.println("Item zero is " + l.get(0));
    }
}
