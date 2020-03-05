package hello.streams;

import student.Student;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectingStudents {
    public static void main(String[] args) {
        List<Student> roster = Arrays.asList(
                Student.ofNameGradeCourses("Fred", 3.2, "Math", "Physics"),
                Student.ofNameGradeCourses("Jim", 2.2, "Art"),
                Student.ofNameGradeCourses("Sheila", 3.8, "Math",
                        "Physics", "Chemistry", "Quantum Mechanics")
        );

        Function<Student, String> studentGradeExtractor =
                s -> {
                    double grade = s.getGrade();
                    if (grade > 3) return "A";
                    else return "D";
                };

        roster.stream()
                .collect(Collectors.groupingBy(studentGradeExtractor))
                .forEach((k,v) -> System.out.println("Students with grade " + k + " are " + v));
        System.out.println("--------------------");

        roster.stream()
                .collect(Collectors.groupingBy(studentGradeExtractor, Collectors.counting()))
                .forEach((k,v) -> System.out.println("There are " + v + " students with grade " + k));
        System.out.println("--------------------");

        roster.stream()
                .collect(Collectors.groupingBy(studentGradeExtractor,
                        Collectors.mapping(Student::getName,
                                Collectors.joining(", "))))
                .forEach((k,v) -> System.out.println(v + " have grade " + k));
        System.out.println("--------------------");
    }
}
