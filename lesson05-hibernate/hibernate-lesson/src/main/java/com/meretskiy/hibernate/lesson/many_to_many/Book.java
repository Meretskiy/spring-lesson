package com.meretskiy.hibernate.lesson.many_to_many;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    //многие ко многим (из листа в лист)
    @ManyToMany
    //связь организованна с поиощью специальной join таблицы books_readers
    @JoinTable(
            //название join таблицы
            name = "books_readers",
            //ссылка на мой текущий класс, какой столбец в этой таблице ссылается на меня
            joinColumns = @JoinColumn(name = "book_id"),
            //ссылка на связанною со мной сущность , какой столбец ссылается на связный класс
            inverseJoinColumns = @JoinColumn(name = "reader_id")
    )
    private List<Reader> readers;

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

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return String.format("Book [id = %d, title = %s]", id, title);
    }
}
