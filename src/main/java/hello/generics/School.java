package hello.generics;

import student.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

interface Criterion<T> {
    boolean test(T s);
}

class SmartCriterion implements Criterion<Student> {
    @Override
    public boolean test(Student s) {
        return s.getGrade() > 3;
    }
}

class EnthusiasticCriterion implements Criterion<Student> {
    @Override
    public boolean test(Student s) {
        return s.getCourses().size() > 3;
    }
}

class LengthCriterion implements Criterion<String> {

    @Override
    public boolean test(String s) {
        return s.length() > 3;
    }
}

public class School {
    public static <T> List<T> filter(Iterable<T> ls, Criterion<T> crit) {
        List<T> out = new ArrayList<>();
        for (T s : ls) {
            if (crit.test(s)) {
                out.add(s);
            }
        }
        return out;
    }

    public static <T> void showAll(List<T> ls) {
        for (T s : ls) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        List<Student> roster = Arrays.asList(
                Student.ofNameGradeCourses("Fred", 3.2, "Math", "Physics"),
                Student.ofNameGradeCourses("Jim", 2.2, "Art"),
                Student.ofNameGradeCourses("Sheila", 3.8, "Math",
                        "Physics", "Chemistry", "Quantum Mechanics")
        );
        System.out.println("---------------------------");
        showAll(filter(roster, new SmartCriterion()));

        System.out.println("---------------------------");
        showAll(filter(roster, new EnthusiasticCriterion()));

        System.out.println("---------------------------");
        List<String> names = Arrays.asList("Fred", "Jim", "Sheila");
        showAll(filter(names, new LengthCriterion()));
    }
}
