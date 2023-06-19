package com.management.motelroom.repository;

import com.management.motelroom.entity.Payment;
import com.management.motelroom.model.dto.SummaryPaymentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    @Query(value = "select p from Payment p where p.username like %:name%")
    Page<Payment> findByName(@Param("name")String name, Pageable pageable);


    @Query(value = "select new com.management.motelroom.model.dto.SummaryPaymentDto(p.username,sum(p.money)) from Payment p where p.username like %:keyword% group by p.username")
    Page<SummaryPaymentDto> findBySummary(@Param("keyword")String keyword,Pageable pageable);
}
