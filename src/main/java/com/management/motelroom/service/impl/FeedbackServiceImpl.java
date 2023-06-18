package com.management.motelroom.service.impl;

import com.management.motelroom.entity.Feedback;
import com.management.motelroom.model.dto.FeedbackDto;
import com.management.motelroom.repository.FeedbackRepository;
import com.management.motelroom.service.FeedbackService;
import com.management.motelroom.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    @Override
    public FeedbackDto getFeedback(Integer id) {
        Feedback feedback = feedbackRepository.findById(id).get();
        return buildDTO(feedback);
    }

    private FeedbackDto buildDTO(Feedback f){
        return FeedbackDto.builder()
                .id(f.getId())
                .email(f.getEmail())
                .content(f.getContent())
                .createdAt(f.getCreatedAt())
                .responded(f.getResponded())
                .build();
    }
    @Override
    public FeedbackDto createFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setEmail(feedbackDto.getEmail());
        feedback.setContent(feedbackDto.getContent());
        feedback.setResponded(0);
        feedback.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        feedback = feedbackRepository.save(feedback);
        return buildDTO(feedback);
    }

    @Override
    public ResponseResult<FeedbackDto> getPage(Integer page, Integer limit) {
        Page<Feedback> feedbackPage = feedbackRepository.findAll(PageRequest.of(page,limit));
        ResponseResult<FeedbackDto> feedbackDtoResponseResult = new ResponseResult<>();
        feedbackDtoResponseResult.setData(feedbackPage.getContent().stream().map(this::buildDTO).collect(Collectors.toList()));
        feedbackDtoResponseResult.setTotalPage(feedbackPage.getTotalPages());
        feedbackDtoResponseResult.setTotalData(feedbackPage.getTotalElements());
        return feedbackDtoResponseResult;
    }

    @Override
    public FeedbackDto readFeedback(Integer id) {
        Feedback feedback = feedbackRepository.findById(id).get();
        feedback.setResponded(1);
        feedback = feedbackRepository.save(feedback);
        return buildDTO(feedback);
    }
}
