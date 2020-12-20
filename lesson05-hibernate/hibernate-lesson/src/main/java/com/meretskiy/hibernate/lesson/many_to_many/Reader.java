package com.meretskiy.hibernate.lesson.many_to_many;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    //многие ко многим (из листа в лист)
    @ManyToMany
    //связь организованна с поиощью специальной join таблицы books_readers
    @JoinTable(
            //название join таблицы
            name = "books_readers",
            //ссылка на мой текущий класс, какой столбец в этой таблице ссылается на меня
            joinColumns = @JoinColumn(name = "reader_id"),
            //ссылка на связанною со мной сущность , какой столбец ссылается на связный класс
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Reader() {
    }

    @Override
    public String toString() {
        return String.format("Reader [id = %d, name = %s]", id, name);
    }
}

