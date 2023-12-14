package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private final String courseName;
    private List<Student> enrolledStudents;

    public Course(String courseName) {
        this.courseName = courseName;
        this.enrolledStudents = new ArrayList<>();
    }

    public void addStudent(Student student) {
        student.setEnrollmentDate(LocalDate.now());
        enrolledStudents.add(student);
    }

    public Student removeStudent(String name) {
        for (Student student : enrolledStudents) {
            if (student.getName().equals(name)) {
                enrolledStudents.remove(student);
                return student;
            }
        }
        return null;
    }

    public boolean isStudentEnrolled(Student student) {
        return enrolledStudents.contains(student);
    }

    @Override
    public String toString() {
        return "[" + courseName + ']';
    }

    public String getCourseName() {
        return courseName;
    }
}