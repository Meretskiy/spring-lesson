package com.meretskiy.hibernate.lesson.one_to_many;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "universities")
//К сущности можно добавлять именнованные запросы
//даем запросу имя withStudents и можем писать запросы HQL
// (мы хотим из университета вытащить объект с именем u и хотим вместе с ним вытащить его студентов,
// а сам университет вытаскиваем по Id)
@NamedQueries({
        @NamedQuery(name = "withStudents", query = "SELECT u FROM University u " +
                "JOIN FETCH u.students WHERE u.id = :id")
})
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    //один университет откносится ко многим студентам
    //ищем студентов по обратной ссылке, т.к. не можем в университете хранить список всех студентов
    //мы знаем что у студентов есть поле университет, которое на нас ссылается - это и есть обратная связь
    //При связи @OneToMany по умолчанию стоит так называемая ленивая загрузка LAZY fetch, это для того
    //что бы при загрузке данного класса мы не тащили все связные с ним классы, а загружали по необходимости,
    //но тут есть ограничение, подгрузиться эти связанные классы смогут только в одной сессии с загрузкой
    //этого класса, или будет LazyInitializationException
    //можно по умолчанию поставить EAGER featch и тогда потянется сразу все ...
    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    //каскадирование
    //если мы сохраняем этот объект это распространяется и на все many
    //и они тоже сохраняются
    //можно перечислять пару операций, алл использовать аккуратно, туда входит del & remove
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Student> students;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public University() {
    }

    @Override
    public String toString() {
        return String.format("University [id = %d, title = %s, students_count = %d]", id, title, students.size());
    }
}

