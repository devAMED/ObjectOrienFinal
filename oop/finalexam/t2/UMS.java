package oop.finalexam.t2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * University Management System.
 *
 * Features:
 *  • Holds a roster of Student objects.
 *  • Lazy-loads Muhammad Ahmed's Argus courses when selected.
 *  • CLI menu lets the user pick which student's details to print.
 */
public class UMS {

    private final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        new UMS().runCLI();
    }

    /* ---------------- CLI runner ---------------- */
    private void runCLI() {
        populateRoster();

        /* ---------- menu ---------- */
        System.out.println("=== University Management System ===");
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.printf("%d) %s %s%n",
                    i + 1, s.getFirstName(), s.getLastName());
        }

        System.out.print("Enter the number of the student you want to view: ");
        int choice = readChoice(1, students.size());

        Student selected = students.get(choice - 1);
        printStudentData(selected);
    }

    /* Reads user input and validates range */
    private static int readChoice(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int n;
        while (true) {
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n >= min && n <= max) break;
            } else {
                sc.next(); // discard non-int token
            }
            System.out.printf("Please enter a number between %d and %d: ", min, max);
        }
        return n;
    }

    /* ---------------- data helpers ---------------- */

    private void populateRoster() {
        /* Muhammad Ahmed (Argus-verified courses will load on demand) */
        students.add(new Student("Muhammad", "Ahmed", "U2025001"));

        Student alice = new Student("Alice", "Johnson", "S12345");
        alice.addCourse(new LearningCourse("Database Systems",
                "Intro to CS", "SQL, Indexing, Transactions"));
        students.add(alice);

        Student bob = new Student("Bob", "Lee", "S67890");
        bob.addCourse(new LearningCourse("Artificial Intelligence",
                "Algorithms", "Search, Logic, Machine Learning"));
        students.add(bob);
    }

    /** Prints one student and every course with full details. */
    public void printStudentData(Student s) {
        ensureArgusCoursesIfSelf(s);

        System.out.println("\nStudent → " + s);
        if (s.getCourses().isEmpty()) {
            System.out.println("  (no courses)");
        } else {
            for (LearningCourse c : s.getCourses()) {
                System.out.println("  ▸ " + c);
            }
        }
    }

    /* Adds Muhammad Ahmed’s Argus courses if this student is “me”. */
    private void ensureArgusCoursesIfSelf(Student s) {
        boolean isSelf =
                s.getFirstName().equalsIgnoreCase("Muhammad") &&
                        s.getLastName().equalsIgnoreCase("Ahmed");

        if (isSelf && s.getCourses().isEmpty()) {
            s.getCourses().addAll(argusCourses());
        }
    }

    /* Exact Argus course list – DO NOT modify titles or prerequisites */
    private static List<LearningCourse> argusCourses() {
        List<LearningCourse> list = new ArrayList<>();

        list.add(new LearningCourse(
                "Calculus 2",
                "Calculus 1",
                "Integration techniques, Series, Polar coordinates"));

        list.add(new LearningCourse(
                "Object Oriented Programming",
                "Intro To Programming",
                "Classes, Inheritance, Polymorphism"));

        list.add(new LearningCourse(
                "Algorithms and Data Structure",
                "Intro To Programming",
                "Sorting, Searching, Trees, Graphs"));

        list.add(new LearningCourse(
                "Mathematical Foundation of Computing",
                "MATH 150 Calculus I",
                "Logic, Sets, Combinatorics, Proof techniques"));

        list.add(new LearningCourse(
                "CS50 Introduction to Programming",
                "—",
                "C, Arrays, Memory, Web, Final project"));

        list.add(new LearningCourse(
                "Computer Organisation",
                "Intro To Programming",
                "CPU, Assembly, Pipelining, Memory hierarchy"));

        return list;
    }
}
