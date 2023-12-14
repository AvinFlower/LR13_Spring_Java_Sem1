package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private final int id;
    private final String name;
    private final String group;
    private final double average;

    private LocalDate enrollmentDate;
    private List<Course> courses = new ArrayList<>();
    private StudentCard studentCard;

    public Student(int id, String name, String group, double average) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.average = average;
    }

    @Override
    public String toString() {
        return "[" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", group = '" + group + '\'' +
                ", average = '" + average + '\'' +
                ']';
    }

    public void addCourse(Course course) {
        courses.add(course);
        if (this.studentCard == null) {
            this.studentCard = new StudentCard();
            this.studentCard.addToCard(this);
        }
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        if (courses.isEmpty() && studentCard != null) {
            studentCard.removeFromCard(this);
            studentCard = null;
        }
    }

    public void removeStudentCardIfNeeded() {
        if (courses.isEmpty() && studentCard != null) {
            studentCard.removeFromCard(this);
            studentCard = null;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public double getAverage() {
        return average;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public StudentCard getStudentCard() {
        return studentCard;
    }

    public void setStudentCard(StudentCard studentCard) {
        this.studentCard = studentCard;
    }
}
