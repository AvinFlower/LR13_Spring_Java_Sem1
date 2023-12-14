package org.example;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentCard {
    private Map<Student, StudentCard> studentCardMap;

    public StudentCard() {
        this.studentCardMap = new HashMap<>();
    }

    public Map<Student, StudentCard> getStudentCardMap() {
        return studentCardMap;
    }

    public void addToCard(Student student) {
        if (!studentCardMap.containsKey(student)) {
            StudentCard studentCard = new StudentCard();
            studentCardMap.put(student, studentCard);
            student.setStudentCard(studentCard);
        } else {
            System.out.println("Студент " + student.getName() + " уже в карте или не найден.");
        }
    }

    public void removeFromCard(Student student) {
        studentCardMap.remove(student);

    }

    public StudentCard getStudentCard(Student student) {
        return studentCardMap.get(student);
    }
}