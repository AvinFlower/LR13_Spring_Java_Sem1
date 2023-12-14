package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentCard studentCard;
    @Autowired
    Request request;
    @Autowired
    MileService mileService;

    private ArrayList<Student> students;

    @PostConstruct
    public void generateListOfStudents() {
        students = new ArrayList<>();
        students.add(new Student(1, "Иванов Иван", "Группа A", 4.5));
        students.add(new Student(2, "Петров Петр", "Группа B", 3.8));
        students.add(new Student(3, "Сидоров Алексей", "Группа C", 4.2));
        students.add(new Student(4, "Козлова Екатерина", "Группа A", 2.9));
        students.add(new Student(5, "Смирнов Сергей", "Группа B", 3.5));
        students.add(new Student(6, "Андреев Андрей", "Группа C", 4.0));
        students.add(new Student(7, "Павлова Ольга", "Группа A", 4.7));
        students.add(new Student(8, "Кузнецов Дмитрий", "Группа B", 3.9));
        students.add(new Student(9, "Новикова Анастасия", "Группа C", 4.4));
        students.add(new Student(10, "Васильев Владимир", "Группа A", 3.6));

    }

    public void printAll() {
        System.out.println("Список студентов:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
    public Student findByName(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public Student findByNameGroup(String name, String group) {
        for (Student student : students) {
            if (student.getName().equals(name) && student.getGroup().equals(group)) {
                return student;
            }
        }
        return null;
    }
    public StudentCard getStudentCard() {
        return studentCard;
    }
}