package com.meretskiy.spring.data.university.repositories;

import com.meretskiy.spring.data.university.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//создали Studentrepository, он является спринг бином и он наследуется от Crudrepository,
//использовать мы его будем для класса Student тип первичного ключа в БД у него Long.
@Repository
public interface StudentsRepository extends CrudRepository<Student, Long> {

    //Найти студента по имени
    Optional<Student> findByName(String name);

    //найти всех по скору больше чем
    List<Student> findAllByScoreGreaterThan(int score);

    //найти всех по скору между мин и макс
    List<Student> findAllByScoreBetween(int min, int max);

    //кастомный метод
    //селект всех студентов где айди = первому параметру(можно указать и имя поля) и имя = второму параметру
    @Query("select s from Student s where s.id = ?1 and s.name = ?2")
    Optional<Student> customFindByIdAndName(Long id, String name);
}
