package com.elearning.option.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.option.entity.Option;
import com.elearning.question.entity.Question;

public interface OptionRepository
        extends JpaRepository<Option, Long> {

    List<Option> findByQuestionOrderByOptionOrderAsc(Question question);

    Optional<Option> findByIdAndQuestion(
            Long id,
            Question question);
    long countByQuestionAndCorrectTrue(Question question);
    
    boolean existsByQuestionAndOptionOrder(Question question,
            Integer optionOrder);

boolean existsByQuestionAndOptionTextIgnoreCase(
Question question,
String optionText);

long countByQuestion(Question question);

}