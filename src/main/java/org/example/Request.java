//package org.example;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@Component
//public class Request {
//
//    public List<Student> studentList = new ArrayList<>();
//    private Map<Student, Course> studentCourseMap = new HashMap<>();
//
//    @Autowired
//    private StudentService studentService;
//    @Autowired
//    private CourseService courseService;
//
//    public boolean add(String name, String group, String courseName) {
//        Student student = studentService.findByNameGroup(name, group);
//
//        if (student != null) {
//            Course course = courseService.findByCourseName(courseName);
//
//            if (course.isStudentEnrolled(student)) {
//                System.out.println("Студент " + student.getName() + " уже принят на данный курс.");
//                return false;
//            }
//
//            if (!studentCourseMap.containsKey(student) || !studentCourseMap.get(student).equals(course)) {
//                // Проверка, что студент не записан на данный курс
//                if (!course.isStudentEnrolled(student)) {
//                    studentCourseMap.put(student, course);
//                    System.out.println(student.getName() + " отправил заявку на курс " + course.getCourseName());
//
//                    if (student.getAverage() > 3.0) {
//                        // Зачисляем студента на курс
//                        course.addStudent(student);
//
//                        LocalDateTime startDate = LocalDateTime.now();
//                        LocalDateTime endDate = startDate.plusDays(60);
//
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//                        String formattedStartDate = startDate.format(formatter);
//                        String formattedEndDate = endDate.format(formatter);
//
//                        System.out.println("Студент " + student.getName() + " принят на курс. Дата принятия заявки: " + formattedStartDate);
//                        System.out.println("Дедлайн: " + formattedEndDate);
//                        return true;
//                    } else {
//                        System.out.println("Средний балл студента недостаточен для подачи заявки.");
//                        return false;
//                    }
//                } else {
//                    System.out.println("Студент " + student.getName() + " уже подавал заявку на данный курс.");
//                    return false;
//                }
//            }
//        } else {
//            System.out.println("Студент не найден.");
//            return false;
//        }
//        return false;
//    }
//
//
//    public void remove(Student student) {
//        studentCourseMap.remove(student);
//    }
//
//}
package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Request {

    public List<Student> studentList = new ArrayList<>();
    private Map<Student, Course> studentCourseMap = new HashMap<>();

    @Autowired
    private StudentService studentService;

    public boolean addRequest(String name, String group, String courseName, CourseService courseService) {
        Student student = studentService.findByNameGroup(name, group);

        if (student != null) {
            Course course = courseService.findByCourseName(courseName);

            if (course != null) {
                return processRequest(student, course, courseService);
            } else {
                System.out.println("Курс не найден.");
                return false;
            }
        } else {
            System.out.println("Студент не найден.");
            return false;
        }
    }

    private boolean processRequest(Student student, Course course, CourseService courseService) {
        if (studentCourseMap.containsKey(student) && studentCourseMap.get(student).equals(course)) {
            System.out.println("Студент " + student.getName() + " уже подавал заявку на данный курс.");
            return false;
        }

        // Проверка, что студент не записан на данный курс
        if (!course.isStudentEnrolled(student)) {
            studentCourseMap.put(student, course);
            System.out.println(student.getName() + " отправил заявку на курс " + course.getCourseName());

            if (student.getAverage() > 3.0) {
                // Зачисляем студента на курс
                course.addStudent(student);
                // Добавляем курс в список курсов студента
                student.addCourse(course);

                LocalDateTime startDate = LocalDateTime.now();
                LocalDateTime endDate = startDate.plusDays(60);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                String formattedStartDate = startDate.format(formatter);
                String formattedEndDate = endDate.format(formatter);

                System.out.println("Студент " + student.getName() + " принят на курс. Дата принятия заявки: " + formattedStartDate);
                System.out.println("Дедлайн: " + formattedEndDate);
                return true;
            } else {
                System.out.println("Средний балл студента недостаточен для подачи заявки.");
                return false;
            }
        } else {
            System.out.println("Студент " + student.getName() + " уже принят на данный курс.");
            return false;
        }
    }


    public void remove(Student student) {
        studentCourseMap.remove(student);
    }
}
