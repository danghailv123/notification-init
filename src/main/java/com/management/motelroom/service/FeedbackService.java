package com.management.motelroom.service;

import com.management.motelroom.model.dto.FeedbackDto;
import com.management.motelroom.utils.ResponseResult;
import org.springframework.http.ResponseEntity;

public interface FeedbackService {
    FeedbackDto getFeedback(Integer id);

    FeedbackDto createFeedback(FeedbackDto feedbackDto);

    ResponseResult<FeedbackDto> getPage(Integer page, Integer limit);

    FeedbackDto readFeedback(Integer id);
}
