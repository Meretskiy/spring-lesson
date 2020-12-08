package com.meretskiy.spring.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {

//        simpleDemoBean();
//        simpleStudentRepository();
//        simpleStudentService();
        simpleBoxBean();

    }

    private static void simpleBoxBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Box box = context.getBean("box", Box.class);
        System.out.println(box.getColor());
        System.out.println(box.getSize());
        System.out.println(box.doSomethingWithStudentService());

        Box box2 = context.getBean("box2", Box.class);
        System.out.println(box2.getColor());
    }

    private static void simpleStudentService() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        StudentService studentService = context.getBean("studentService", StudentService.class);
        System.out.println(studentService.calculateAverageScore());

        context.close();
    }

    private static void simpleStudentRepository() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        StudentInMemoryRepository studentInMemoryRepository = context.getBean("studentInMemoryRepository", StudentInMemoryRepository.class);
        //без unmodifiableList с легкостью сможем подменять значения
        //studentRepository.getStudents().add(new Student(3L, "Tom", 1000));
        studentInMemoryRepository.add(new Student(3L, "Tom", 50));
        System.out.println(studentInMemoryRepository.getStudents());

        context.close();
    }

    public static void simpleDemoBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        DemoBean demoBean = context.getBean("demoBean", DemoBean.class);
        System.out.println(demoBean.getTitle());
        demoBean.setTitle("1");

        DemoBean demoBean1 = context.getBean("demoBean", DemoBean.class);
        System.out.println(demoBean1.getTitle());

        context.close();
    }
}
