package hello.generics;

import student.Student;

import java.util.Arrays;
import java.util.List;

public class MoreUseSuperIterable {
    public static void main(String[] args) {
        SuperIterable<Student> roster = new SuperIterable<>(Arrays.asList(
                Student.ofNameGradeCourses("Fred", 3.2, "Math", "Physics"),
                Student.ofNameGradeCourses("Jim", 2.2, "Art"),
                Student.ofNameGradeCourses("Sheila", 3.8, "Math",
                        "Physics", "Chemistry", "Quantum Mechanics")));

        // Print all the students who:
        // - have grades > 3
        roster
                .filter(s -> s.getGrade() > 3)
                .map(s -> s.getName() + " has grade " + s.getGrade() + " and takes " + s.getCourses())
                .forEach(s -> System.out.println(s));
        System.out.println("-------------------------");
        // - take more than 2 courses
        roster
                .filter(s -> s.getCourses().size() > 2)
                .forEach(s -> System.out.println(s));
        System.out.println("-------------------------");
        // Print the name and grade of all the students who take more than two courses
        roster
                .filter(s -> s.getCourses().size() > 2)
                .map(s -> s.getName() + " has grade " + s.getGrade())
                .forEach(s -> System.out.println(s));
        System.out.println("-------------------------");

        // Print all the courses taken by the students???
        roster
//                .map(s -> s.getCourses()) // Kinda, but no cigar!
                .flatMap(s -> new SuperIterable(s.getCourses()))
                .forEach(l -> System.out.println(l));
        System.out.println("-------------------------");

        // Print all student-name/course pairs, e.g.
        //   Fred takes Math
        //   Fred takes Physics
        //   ...
        roster
                .flatMap(s -> new SuperIterable(s.getCourses()).map(c -> s.getName() + " takes " + c))
                .forEach(l -> System.out.println(l));
        System.out.println("-------------------------");

        // Print all the smart student's names in a comma separated list: Fred, Sheila
        String res = roster
                .filter(s -> s.getGrade() > 3)
                .map(s -> s.getName())
                .reduce("", (a, b) -> a + ", " + b);
        System.out.println("Smart students are: " + res);
    }
}
