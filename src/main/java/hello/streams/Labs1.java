package hello.streams;

import student.Student;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Labs1 {
    public static void main(String[] args) {
        List<Student> roster = Arrays.asList(
                Student.ofNameGradeCourses("Fred", 3.2, "Math", "Physics"),
                Student.ofNameGradeCourses("Jim", 2.2, "Art"),
                Student.ofNameGradeCourses("Sheila", 3.8, "Math",
                        "Physics", "Chemistry", "Quantum Mechanics")
        );

        System.out.println("1) ----------------------------");
        roster.stream()
                .forEach(s -> System.out.println(s));

        System.out.println("2) ----------------------------");
        roster.stream()
                .map(s -> s.getName())
                .forEach(s -> System.out.println(s));

        System.out.println("3) ----------------------------");
        roster.stream()
                .filter(s -> s.getGrade() > 3)
                .map(s -> s.getName())
                .forEach(s -> System.out.println(s));

        System.out.println("4) ----------------------------");
        roster.stream()
                .filter(s -> s.getGrade() > 3)
                .map(s -> s.getName() + " got grade " + s.getGrade())
                .forEach(s -> System.out.println(s));

        System.out.println("5) ----------------------------");
        roster.stream()
                .flatMap(s -> s.getCourses().stream())
                .forEach(s -> System.out.println(s));

        System.out.println("6) ----------------------------");
        roster.stream()
                .flatMap(s -> s.getCourses().stream())
                .sorted((s1, s2) -> s2.compareTo(s1))
                .forEach(s -> System.out.println(s));

        System.out.println("7) ----------------------------");
        roster.stream()
                .flatMap(s -> s.getCourses().stream())
                .distinct()
                .sorted((s1, s2) -> s2.compareTo(s1))
                .forEach(s -> System.out.println(s));

        System.out.println("8) ----------------------------");
        Function<Student, Stream<String>> extractNameCoursePairs =
                s -> s.getCourses().stream().map(c -> s.getName() + " takes " + c);
        roster.stream()
                .flatMap(extractNameCoursePairs)
                .forEach(s -> System.out.println(s));

        System.out.println("9) ----------------------------");
        DoubleSummaryStatistics dss = roster.stream()
                .mapToDouble(s -> s.getGrade())
                .summaryStatistics();
        System.out.println(dss);

        System.out.println("9a) ----------------------------");
        class Avg {
            public long count = 0;
            public double sum = 0;
            public Avg(long count, double sum) {
                this.count = count;
                this.sum = sum;
            }
            public double get() {
                return sum / count;
            }
        }

        Avg resultAverage = roster.stream()
                .map(s -> new Avg(1, s.getGrade()))
                .reduce(
                        new Avg(0, 0),
                        (a1, a2) -> new Avg(a1.count + a2.count, a1.sum + a2.sum)
                );
        System.out.println("Average is " + resultAverage.get());
    }
}
