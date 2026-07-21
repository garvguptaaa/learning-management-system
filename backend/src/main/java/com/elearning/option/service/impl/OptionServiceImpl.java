package com.elearning.option.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.option.dto.request.CreateOptionRequest;
import com.elearning.option.dto.response.OptionResponse;
import com.elearning.option.mapper.OptionMapper;
import com.elearning.option.repository.OptionRepository;
import com.elearning.option.service.OptionService;
import com.elearning.question.repository.QuestionRepository;
import com.elearning.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final OptionMapper optionMapper;

    @Override
    public OptionResponse createOption(Long questionId,
                                       String email,
                                       CreateOptionRequest request) {

        return null;
    }
}