package com.elearning.option.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.option.dto.request.CreateOptionRequest;
import com.elearning.option.dto.response.OptionResponse;
import com.elearning.option.mapper.OptionMapper;
import com.elearning.option.repository.OptionRepository;
import com.elearning.option.service.OptionService;
import com.elearning.question.repository.QuestionRepository;
import com.elearning.user.repository.UserRepository;

import com.elearning.common.enums.RoleType;
import com.elearning.common.exception.BadRequestException;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.option.entity.Option;
import com.elearning.question.entity.Question;
import com.elearning.user.entity.User;

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

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        boolean isAdmin = user.getRoles()
                .stream()
                .anyMatch(role -> role.getName() == RoleType.ADMIN);

        Question question;

        if (isAdmin) {
            question = questionRepository.findById(questionId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Question not found"));
        } else {
            question = questionRepository
                    .findByIdAndQuizLessonModuleCourseInstructorEmail(
                            questionId,
                            email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Question not found or you are not the owner"));
        }

        if (optionRepository.existsByQuestionAndOptionOrder(
                question,
                request.getOptionOrder())) {

            throw new BadRequestException(
                    "Option order already exists");
        }

        if (optionRepository.existsByQuestionAndOptionTextIgnoreCase(
                question,
                request.getOptionText())) {

            throw new BadRequestException(
                    "Option already exists");
        }

        Option option = optionMapper.toEntity(request);

        option.setQuestion(question);

        Option savedOption = optionRepository.save(option);

        return optionMapper.toResponse(savedOption);
    }
}