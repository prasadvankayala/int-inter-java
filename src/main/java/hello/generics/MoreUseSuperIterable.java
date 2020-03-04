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
        // - take more than 2 courses
        // Print the name and grade of all the students who take more than two courses

        // Print all the courses taken by the students???
        // Print all student-name/course pairs, e.g.
        //   Fred takes Math
        //   Fred takes Physics
        //   ...
        // Print all the smart student's names in a comma separated list: Fred, Sheila

    }
}
