package com.management.motelroom.service.impl;

import com.management.motelroom.entity.Feedback;
import com.management.motelroom.entity.Payment;
import com.management.motelroom.model.dto.FeedbackDto;
import com.management.motelroom.model.dto.PaymentDto;
import com.management.motelroom.model.dto.SummaryPaymentDto;
import com.management.motelroom.repository.PaymentRepository;
import com.management.motelroom.service.PaymentService;
import com.management.motelroom.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    @Override
    public PaymentDto getPayment(Integer id) {
        Payment payment = paymentRepository.findById(id).get();
        return buildDTO(payment);
    }

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setIs_(paymentDto.getIs());
        payment.setType(paymentDto.getType());
        payment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        payment.setUsername(paymentDto.getUsername());
        payment.setMoney(paymentDto.getMoney());
        payment = paymentRepository.save(payment);
        return buildDTO(payment);
    }

    @Override
    public Integer deletePayment(Integer id) {
        paymentRepository.deleteById(id);
        return id;
    }

//    @Override
//    public PaymentDto updatePayment(PaymentDto paymentDto) {
//        return null;
//    }

    @Override
    public ResponseResult<PaymentDto> getPage(String username, Integer page, Integer limit) {
        Page<Payment> payments = paymentRepository.findByName(username, PageRequest.of(page,limit));
        ResponseResult<PaymentDto> paymentDtoResponseResult = new ResponseResult<>();
        paymentDtoResponseResult.setData(payments.getContent().stream().map(this::buildDTO).collect(Collectors.toList()));
        paymentDtoResponseResult.setTotalPage(payments.getTotalPages());
        paymentDtoResponseResult.setTotalData(payments.getTotalElements());
        return paymentDtoResponseResult;
    }

    @Override
    public ResponseResult<SummaryPaymentDto> getPageSummary(String keyword, Integer page, Integer limit) {
        Page<SummaryPaymentDto> payments = paymentRepository.findBySummary(keyword, PageRequest.of(page,limit));
        ResponseResult<SummaryPaymentDto> summaryPaymentDtoResponseResult = new ResponseResult<>();
        summaryPaymentDtoResponseResult.setData(payments.getContent());
        summaryPaymentDtoResponseResult.setTotalPage(payments.getTotalPages());
        summaryPaymentDtoResponseResult.setTotalData(payments.getTotalElements());
        return summaryPaymentDtoResponseResult;
    }

    private PaymentDto buildDTO(Payment f){
        return PaymentDto.builder()
                .id(f.getId())
                .is(f.getIs_())
                .type(f.getType())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .username(f.getUsername())
                .money(f.getMoney())
                .build();
    }
}
