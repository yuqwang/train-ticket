package com.trainticket.service;

import com.trainticket.domain.AddMoney;
import com.trainticket.domain.AddMoneyInfo;
import com.trainticket.domain.Payment;
import com.trainticket.domain.PaymentInfo;
import com.trainticket.repository.AddMoneyRepository;
import com.trainticket.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/6/23.
 */
@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    AddMoneyRepository addMoneyRepository;

    @Override
    public boolean pay(PaymentInfo info, HttpHeaders headers){
        if(paymentRepository.findByOrderId(info.getOrderId()) == null){

            /*----------------------
              ----- CPU Defect------
              ----------------------*/
            //injectCPUDefect();

            Payment payment = new Payment();
            payment.setOrderId(info.getOrderId());
            payment.setPrice(info.getPrice());
            payment.setUserId(info.getUserId());
            paymentRepository.save(payment);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean addMoney(AddMoneyInfo info, HttpHeaders headers){
        AddMoney addMoney = new AddMoney();

        /*----------------------
          ----- OOM Defect------
          ----------------------*/
        injectMemoryDefect(2);

        addMoney.setUserId(info.getUserId());
        addMoney.setMoney(info.getMoney());
        addMoneyRepository.save(addMoney);
        return true;
    }

    @Override
    public List<Payment> query(HttpHeaders headers){
        return paymentRepository.findAll();
    }

    @Override
    public void initPayment(Payment payment, HttpHeaders headers){
        Payment paymentTemp = paymentRepository.findById(payment.getId());
        if(paymentTemp == null){
            paymentRepository.save(payment);
        }else{
            System.out.println("[Payment Service][Init Payment] Already Exists:" + payment.getId());
        }
    }

    private void injectMemoryDefect(int defectType) {
        Set<Payment> payments = new HashSet<>();
        Set<AddMoney> addMonies = new HashSet<>();


        switch (defectType) {
            case 1:
                for (int i = 0; i < 10000000; i++) {
                    Payment payment = new Payment();
                    payment.setId(i + "");
                    payment.setOrderId(i + "");
                    payment.setPrice("Test");
                    payment.setUserId(i + "");
                    payments.add(payment);
                }
                break;
            case 2:
                for (int i = 0; i < 10000000; i++) {
                    AddMoney addMoney = new AddMoney();
                    addMoney.setMoney(i + "");
                    addMoney.setUserId(i + "");
                    addMonies.add(addMoney);
                }
                break;
            default:
                break;
        }
    }
}
