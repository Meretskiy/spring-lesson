package com.meretskiy.spring.security.project.services;

import com.meretskiy.spring.security.project.entities.Score;
import com.meretskiy.spring.security.project.repositories.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final ScoreRepository scoreRepository;

    public Optional<Score> findScoresByUserId(Long id) {
        return scoreRepository.findScoresByUser_Id(id);
    }
}
