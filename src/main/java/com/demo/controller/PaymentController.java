package com.demo.controller;

import com.demo.model.Payment;
import com.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @RequestMapping("/payments")
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @RequestMapping("/sumOfPayments/{contractId}")
    public Map getSumOfPayments(@PathVariable int contractId) {
        return paymentRepository.getSumOfPayments(contractId);
    }


// 1. Fetch the list of payments above (which were made in a particular time frame) with the following params:
//    contractId, startDate, endDate
//    {"createdAt":"2020-01-09",
//     "updatedAt":"2020-04-09"}
        @RequestMapping("/paymentsInTimeFrame/{contractId}")
        public List<Payment> getAllPaymentsInTimeFrame(@PathVariable int contractId, @RequestBody Map<String, String> dates) {
            return paymentRepository.findAllPaymentsInTimeFrame(contractId, dates);
        }

//      2. Add a new payment to a contract
//    {"contractId":15555,
//        "description":"Rent payment",
//        "value":300,
//        "time":"2020-05-05",
//        "isImported":false,
//        "createdAt":"2020-05-05",
//        "updatedAt":"2020-05-05",
//        "isDeleted":false}
        @PostMapping(value = "/payments/add")
        public List<Payment> persist(@RequestBody Payment payment) {
            paymentRepository.save(payment);
            return paymentRepository.findAll();
        }

//      3. Update an existing payment
        @PostMapping(value = "/payments/update/{paymentId}")
        public List<Payment> update(@PathVariable int paymentId, @RequestBody Payment payment) {
            paymentRepository.update(paymentId, payment);
            return paymentRepository.findAll();
        }

    //      4. Delete a payment
    @RequestMapping("/payments/delete/{paymentId}")
    public void deletePayment(@PathVariable int paymentId) {
        paymentRepository.delete(paymentId);
    }

}
