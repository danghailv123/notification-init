package com.management.motelroom.controller;


import com.management.motelroom.model.dto.PaymentDto;
import com.management.motelroom.model.dto.SummaryPaymentDto;
import com.management.motelroom.service.PaymentService;
import com.management.motelroom.utils.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> paymentDtoResponseEntity(@PathVariable Integer id) {
        return ResponseEntity.ok().body(paymentService.getPayment(id));
    }

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto){
        return ResponseEntity.ok().body(paymentService.createPayment(paymentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deletePayment(@PathVariable Integer id){
        return ResponseEntity.ok().body(paymentService.deletePayment(id));
    }

//    @PutMapping
//    public ResponseEntity<PaymentDto> update(@RequestBody PaymentDto paymentDto){
//        return ResponseEntity.ok().body(paymentService.updatePayment(paymentDto));
//    }

    @GetMapping
    public ResponseResult<PaymentDto> paymentDtoResponseEntity(@RequestParam(name = "username")String username,
                                                   @RequestParam(name = "page",defaultValue = "0")Integer page,
                                                   @RequestParam(name = "limit",defaultValue = "30")Integer limit){
        return paymentService.getPage(username,page,limit);
    }

    @GetMapping("/summary")
    public ResponseResult<SummaryPaymentDto> summaryPaymentDtoResponseResult(@RequestParam(name = "keyword",defaultValue = "")String keyword,
                                                                             @RequestParam(name = "page",defaultValue = "0")Integer page,
                                                                             @RequestParam(name = "limit",defaultValue = "30")Integer limit){
        return paymentService.getPageSummary(keyword,page,limit);
    }
}
