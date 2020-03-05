package closures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public final class Student {
    private final String name;
    private final double grade;
    private final List<String> courses;

    private Student(String name, double grade, List<String> courses) {
        validate(name, grade, courses);
        this.name = name;
        this.grade = grade;
        this.courses = courses;
    }

    public static Student ofNameGradeCourses(String name, double grade, String ... courses) {
        return new Student(name, grade, Arrays.asList(courses));
    }
    private void validate(String name, double grade, List<String> courses) {
        if (name == null || grade < 0 || grade > 4.0 || courses == null) {
            throw new IllegalArgumentException("Bad student values");
        }
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    public List<String> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public Student addCourse(String course) {
        List<String> newCourses = new ArrayList<>(courses);
        newCourses.add(course);
        return new Student(this.name, this.grade, newCourses);
    }

    public static Predicate<Student> getSmartPredicate(final double threshold) {
//        threshold ++;
        return s -> s.grade > threshold;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", courses=" + courses +
                '}';
    }
}
