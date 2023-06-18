package com.management.motelroom.service;

import com.management.motelroom.model.dto.PaymentDto;
import com.management.motelroom.model.dto.SummaryPaymentDto;
import com.management.motelroom.utils.ResponseResult;

public interface PaymentService {
    PaymentDto getPayment(Integer id);

    PaymentDto createPayment(PaymentDto paymentDto);

    Integer deletePayment(Integer id);

//    PaymentDto updatePayment(PaymentDto paymentDto);

    ResponseResult<PaymentDto> getPage(String username, Integer page, Integer limit);

    ResponseResult<SummaryPaymentDto> getPageSummary(String keyword, Integer page, Integer limit);
}
