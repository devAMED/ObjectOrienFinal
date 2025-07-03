package oop.finalexam.t2;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private final String firstName;
    private final String lastName;
    private final String id;                 // university ID or similar
    private final List<LearningCourse> courses = new ArrayList<>();

    public Student(String firstName, String lastName, String id) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.id        = id;
    }

    /* Basic getters */
    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName; }
    public String getId()        { return id; }

    public List<LearningCourse> getCourses() { return courses; }
    public void addCourse(LearningCourse c)  { courses.add(c); }

    @Override public String toString() {
        return firstName + " " + lastName + " (" + id + ")";
    }
}
