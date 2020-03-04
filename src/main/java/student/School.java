package student;

import java.util.*;

class SmartnessComparator implements Comparator<Student> {
    @Override
    public int compare(Student student, Student t1) {
        return Double.compare(student.getGrade(), t1.getGrade());
    }
}

interface StudentCriterion {
    boolean test(Student s);
}

interface StringCriterion {
    boolean test(String s);
}

class SmartCriterion implements StudentCriterion {
    @Override
    public boolean test(Student s) {
        return s.getGrade() > 3;
    }
}

class EnthusiasticCriterion implements StudentCriterion {
    @Override
    public boolean test(Student s) {
        return s.getCourses().size() > 3;
    }
}

public class School {
//    public static List<Student> getSmart(List<Student> ls, double threshold) {
//        List<Student> out = new ArrayList<>();
//        for (Student s : ls) {
//            if (s.getGrade() > threshold) {
//                out.add(s);
//            }
//        }
//        return out;
//    }

    public static List<Student> filter(List<Student> ls, StudentCriterion crit) {
        List<Student> out = new ArrayList<>();
        for (Student s : ls) {
            if (crit.test(s)) {
                out.add(s);
            }
        }
        return out;
    }

    public static List<String> filter(List<String> ls, StringCriterion crit) {
        List<String> out = new ArrayList<>();
        for (String s : ls) {
            if (crit.test(s)) {
                out.add(s);
            }
        }
        return out;
    }

    public static void showAll(List<Student> ls) {
        for (Student s : ls) {
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
        roster.sort(new SmartnessComparator());
        showAll(roster);

//        roster.add(Student.ofNameGradeCourses("Alan", 3.4, "Math"));
        System.out.println("---------------------------");
        showAll(filter(roster, new SmartCriterion()));

        System.out.println("---------------------------");
        showAll(filter(roster, new EnthusiasticCriterion()));

        System.out.println("---------------------------");
        List<String> names = Arrays.asList("Fred", "Jim", "Sheila");
    }
}
