package com.meretskiy.spring.security.project.repositories;

import com.meretskiy.spring.security.project.entities.Score;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ScoreRepository  extends CrudRepository<Score, Long> {

    Optional<Score> findScoresByUser_Id(Long userId);
}
