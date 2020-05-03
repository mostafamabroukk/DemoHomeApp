package com.demo.service;

import com.demo.model.Payment;
import com.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentService implements PaymentRepository{

    @Autowired
    private JdbcTemplate jtm;

    @Override
    public List<Payment> findAll() {
        String sql = "SELECT * FROM payments p";
        return jtm.query(sql, new BeanPropertyRowMapper<>(Payment.class));
    }

    @Override
    public List<Payment> findAllPaymentsInTimeFrame(int contractId, Map<String, String> dates) {
        String startDate = dates.get("startDate");
        String endDate = dates.get("endDate");
        String sql = "SELECT * FROM payments WHERE contractId = " + contractId
                + " AND createdAt between '" +startDate
                + "' AND '" +endDate + "'";
        return jtm.query(sql, new BeanPropertyRowMapper<>(Payment.class));
    }

    @Override
    public Map getSumOfPayments(int contractId) {
        int sum = 0;
        String sql = "SELECT * FROM payments WHERE contractId = " + contractId;
        List<Payment> allPayments =  jtm.query(sql, new BeanPropertyRowMapper<>(Payment.class));
        for(Payment p : allPayments) {
            sum = sum + p.getValue();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("sum:", sum);
        linkedHashMap.put("items:", allPayments);

        return linkedHashMap;
    }

    @Override
    public void delete(int paymentId) {
        String sql = "DELETE FROM payments WHERE id =" + paymentId;
        jtm.execute(sql);
    }

    @Override
    public void save(Payment payment) {
        String sql = "INSERT INTO payments (contractId, description, value, time, " +
                     "isImported, createdAt, updatedAt, isDeleted) VALUES ("
                    + payment.getContractId() + ",'"
                    + payment.getDescription() +"','"
                    + payment.getValue() + "','"
                    + payment.getTime() +"',"
                    + payment.getIsImported() + ",'"
                    + payment.getCreatedAt() +"','"
                    + payment.getUpdatedAt() + "',"
                    + payment.getIsDeleted() +")";
        jtm.execute(sql);
    }

    @Override
    public void update(int paymentId, Payment payment) {
        String sql = "UPDATE payments " +
                "set contractId = " + payment.getContractId() +
                ", description = '" + payment.getDescription() +
                "', value = " + payment.getValue() +
                ", time = '" + payment.getTime() +
                "', isImported = " + payment.getIsImported() +
                ", createdAt = '" + payment.getCreatedAt() +
                "', updatedAt = '" + payment.getUpdatedAt() +
                "', isDeleted = " + payment.getIsDeleted() +
                " where id = "+ paymentId;
        jtm.execute(sql);
    }
}