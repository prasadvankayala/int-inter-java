package closures;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class School {

    public static <T> Predicate<T> negate(Predicate<T> ps) {
        return s -> !ps.test(s);
    }

    public static void main(String[] args) {
        List<Student> roster = Arrays.asList(
                Student.ofNameGradeCourses("Fred", 3.2, "Math", "Physics"),
                Student.ofNameGradeCourses("Jim", 2.2, "Art"),
                Student.ofNameGradeCourses("Sheila", 3.8, "Math",
                        "Physics", "Chemistry", "Quantum Mechanics")
        );

        roster.stream()
                .filter(negate(Student.getSmartPredicate(3.5)))
                .forEach(System.out::println);


        Predicate<String> longString = s -> s.length() > 3;
        Arrays.asList("Fred", "Jim", "Sheila").stream()
                .filter(negate(longString))
                .forEach(System.out::println);

    }
}
