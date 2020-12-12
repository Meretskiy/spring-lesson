package com.meretskiy.spring.mvc.repositories;

import com.meretskiy.spring.mvc.model.Box;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BoxRepository {
    private List<Box> boxes;

    @PostConstruct
    public void init() {
        boxes = new ArrayList<>();
        boxes.add(new Box(1L, "White", 25));
        boxes.add(new Box(2L, "Red", 15));
        boxes.add(new Box(3L, "Blue", 5));
        boxes.add(new Box(4L, "Green", 35));
    }

    public List<Box> getAllBoxes() {
        return Collections.unmodifiableList(boxes);
    }

    public void save(Box box) {
        boxes.add(box);
    }

    public void deleteById(Long id) {
        boxes.removeIf(b -> b.getId().equals(id));
    }
}
