package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CourseService {
    private ArrayList<Course> courses;

    @Autowired
    Request request;
    @Autowired
    MileService mileService;
    @Autowired
    StudentCard studentsCard;
    @Autowired
    private StudentService studentService;

    @PostConstruct
    public void generateListOfCourses() {
        courses = new ArrayList<>();
        courses.add(new Course("Java"));
        courses.add(new Course("Python"));
        courses.add(new Course("C#"));
        courses.add(new Course("Database Developer"));
        courses.add(new Course("Data Science"));
        courses.add(new Course("Data Analyst"));
        courses.add(new Course("Game Developer"));
        courses.add(new Course("Backend Developer"));
        courses.add(new Course("Android Developer"));
        courses.add(new Course("Data Engineer"));
    }

    public void printAll() {
        System.out.println("Список курсов:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public Course findByCourseName(String courseName) {
        for (Course course : courses) {
            if (course.getCourseName().equals(courseName)) {
                return course;
            }
        }
        return null;
    }

    public void setRequest(String name, String group, String courseName) {
        Student student = studentService.findByNameGroup(name, group);

        if (student != null) {
            Course course = findByCourseName(courseName);

            if (course != null) {
                request.addRequest(name, group, courseName, this);
                if (!student.getCourses().isEmpty()) {
//                mileService.sendEmail(student);
                    mileService.sendEmail(student, course);
                } else {
                    System.out.println("Студент " + student.getName() + " не записан ни на один курс. Письмо не отправлено.");
                }
            } else {
                System.out.println("Курс не найден.");
            }
        } else {
            System.out.println("Студент не найден.");
        }
    }

    public void expelStudent(String name, String courseName) {
        Course course = findByCourseName(courseName);

        if (course != null) {
            Student expelledStudent = course.removeStudent(name);

            if (expelledStudent != null) {
                expelledStudent.removeCourse(course);

                expelledStudent.removeStudentCardIfNeeded();

                // Перебираем все курсы, чтобы узнать, на каких еще курсах числится студент
                int enrolledCourses = 0;
                for (Course c : courses) {
                    if (c.isStudentEnrolled(expelledStudent)) {
                        enrolledCourses++;
                    }
                }

                // Если студент не числится на других курсах, удаляем из карты
                if (enrolledCourses == 0 && expelledStudent.getStudentCard() != null) {
                    request.remove(expelledStudent);
                    expelledStudent.getStudentCard().removeFromCard(expelledStudent);
                    System.out.println("Студент " + expelledStudent.getName() + " закончил курс " + courseName);
                    System.out.println("У студента " + expelledStudent.getName() + " больше нет курсов, карта студента удалена.");
                } else {
                    request.remove(expelledStudent);
                    System.out.println("Студент " + expelledStudent.getName() + " закончил курс " + courseName);
                }
            } else {
                System.out.println("Студент " + name + " не найден на курсе " + courseName);
            }
        } else {
            System.out.println("Курс " + courseName + " не найден");
        }
    }
}