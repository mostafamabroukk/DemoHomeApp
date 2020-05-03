package com.demo.repository;

import com.demo.model.Payment;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface PaymentRepository {

    List<Payment> findAll();
    List<Payment> findAllPaymentsInTimeFrame(int contractId, Map<String, String> dates);
    Map getSumOfPayments(int contractId);
    void delete(int paymentId);
    void save(Payment payment);
    void update(int paymentId, Payment payment);

}