package com.meretskiy.spring.security.project.repositories;

import com.meretskiy.spring.security.project.entities.Score;
import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository  extends CrudRepository<Score, Long> {
}
