package com.meretskiy.spring.security.project.services;

import com.meretskiy.spring.security.project.entities.Score;
import com.meretskiy.spring.security.project.entities.User;
import com.meretskiy.spring.security.project.repositories.ScoreRepository;
import com.meretskiy.spring.security.project.repositories.UserRepository;
import exception_handling.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;

    public int findQuantityByUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User by id '%s' not found", id)));
        return user.getScore().getQuantity();

    }

    public int updateQuantityInc(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User by name '%s' not found", principal.getName())));
        Score score = user.getScore();
        int quantity = score.getQuantity();
        score.setQuantity(quantity + 1);
        scoreRepository.save(score);
        return quantity + 1;

    }

    public int updateQuantityDec(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User by name '%s' not found", principal.getName())));
        Score score = user.getScore();
        int quantity = score.getQuantity();
        score.setQuantity(quantity - 1);
        scoreRepository.save(score);
        return quantity - 1;
    }


    public int getCurrentScore(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User by name '%s' not found", principal.getName())));
        return user.getScore().getQuantity();
    }
}
