package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CourseService courseService = context.getBean("courseService", CourseService.class);

        // Выводим список всех курсов
        //courseService.printAll();

        //Добавляем студента с низким баллом
        System.out.println("===================================================================================");
        courseService.setRequest("Козлова Екатерина", "Группа A", "Android Developer");

        // Добавляем студента на курсы
        System.out.println("===========");
        courseService.setRequest("Иванов Иван", "Группа A", "C#");
        System.out.println("===========");
        courseService.setRequest("Иванов Иван", "Группа A", "Python");
        System.out.println("===========");
        courseService.setRequest("Иванов Иван", "Группа A", "Data Engineer");

        //Попытаемся повторно добавить студента на тот курс, в котором он уже состоит
//        System.out.println("===========");
//        courseService.setRequest("Иванов Иван", "Группа A", "C#");

        // Отчисляем студента
        System.out.println("===========");
        courseService.expelStudent("Иванов Иван", "C#");
    }
}