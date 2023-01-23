package com.allstate.payments.service;

import com.allstate.payments.data.PaymentRepository;
import com.allstate.payments.domain.Payment;
import com.allstate.payments.exceptions.PaymentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

//    @Autowired
//    public void setPaymentRepository(PaymentRepository paymentRepository) {
//        this.setPaymentRepository(paymentRepository);
//    }

//    public PaymentServiceImpl(PaymentRepository paymentRepository) {
//        this.paymentRepository = paymentRepository;
//    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> payments =paymentRepository.findAll();
        System.out.println("There were " + payments.size() + " found");
        return payments;
    }

    @Override
    public Payment getById(Integer id) throws PaymentNotFoundException {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            return optionalPayment.get();
        }
        else {
            throw new PaymentNotFoundException("There is no payment with id " + id);
        }

    }

    @Override
    public List<Payment> getByCountry(String country) {
        return paymentRepository.findAllByCountry(country);
    }

    @Override
    public List<Payment> getByOrder(String order) {
        return paymentRepository.findAllByOrderId(order);
    }

    @Override
    public List<String> getAllCountries() {
//        List<Payment> allPayments = paymentRepository.findAll();
//        Set<String> uniqueCountries = new HashSet<>();
//        for (Payment payment : allPayments) {
//            uniqueCountries.add(payment.getCountry());
//        }
//        List<String> countries = new ArrayList<>(uniqueCountries);
//        return countries;

        return paymentRepository.findAll().stream()
                .map( payment -> payment.getCountry().toLowerCase())
                .distinct()
                .collect(Collectors.toList());

    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Integer id, Map<String, Object> fields) {
        //load the existing payment
        Payment payment = paymentRepository.findById(id).get(); //should really check it is there + throw an exception

        //update those fields that have changed
        if (fields.containsKey("country")) {
            payment.setCountry(fields.get("country").toString());
        }
        if (fields.containsKey("amount")) {
            //any logic eg is amount > 0?
            payment.setAmount(Double.parseDouble(fields.get("amount").toString()));
        }

//        for (String field : fields.keySet()) {
//            switch(field) {
//                case "country" : payment....
//                                break;
//                
//            }
//        }

        //save and return the payment
        return paymentRepository.save(payment);
    }
}
