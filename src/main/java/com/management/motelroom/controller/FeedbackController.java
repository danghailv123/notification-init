package com.management.motelroom.controller;

import com.management.motelroom.model.dto.FeedbackDto;
import com.management.motelroom.service.FeedbackService;
import com.management.motelroom.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/feedback")
@CrossOrigin
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping("{id}")
    public ResponseEntity<FeedbackDto> feedbackDtoResponseEntity(@PathVariable Integer id) {
        return ResponseEntity.ok().body(feedbackService.getFeedback(id));
    }

    @PostMapping
    public ResponseEntity<FeedbackDto> createFeedback(@RequestBody FeedbackDto feedbackDto) {
        return ResponseEntity.ok().body(feedbackService.createFeedback(feedbackDto));
    }

    @GetMapping
    public ResponseResult<FeedbackDto> getPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                               @RequestParam(name = "limit", defaultValue = "30") Integer limit) {
        return feedbackService.getPage(page, limit);
    }
}
