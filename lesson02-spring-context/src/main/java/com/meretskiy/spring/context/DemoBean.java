package com.meretskiy.spring.context;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//размечаем бин для спринга
@Component
@Scope(scopeName = "singleton") //если даже не указывать идет по умолчанию - бин будет создан в 1 экземпляре.
//@Scope(scopeName = "prototype") // изменения данного бина не будут влиять на другие бины данного типа.
public class DemoBean {
//    private String title = "abcd"; //не хорошо так заполнять поля, лучше создать метод с аннотацией @PostConstruct
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @PostConstruct
    public void init() {
        title = "abcd";
    }
}
