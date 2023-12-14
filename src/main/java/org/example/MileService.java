package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MileService {

//    public void sendEmail(Student student) {
//        String studentEmail = "stud156169@vyatsu.ru";
//        System.out.println("Письмо отправлено на адрес " + studentEmail + " студенту " + student.getName());
//        printStudentCourses(student.getCourses());
//    }
//
//    private void printStudentCourses(List<Course> courses) {
//        if (courses != null && !courses.isEmpty()) {
//            System.out.println("Вы записан на следующие курсы:");
//            for (Course course : courses) {
//                System.out.println("[" + course.getCourseName() + "]");
//            }
//        } else {
//            System.out.println("В вашей карте нет курсов");
//        }
//    }
public void sendEmail(Student student, Course course) {
    String studentEmail = "stud156169@vyatsu.ru";
    System.out.println("Письмо отправлено на адрес " + studentEmail + " студенту " + student.getName());
    printStudentCourses(course);
}

    private void printStudentCourses(Course course) {
        if (course != null) {
            System.out.println("Вы записаны на следующий курс:");
            System.out.println("[" + course.getCourseName() + "]");
        } else {
            System.out.println("В вашей карте нет курсов");
        }
    }
}