package com.allstate.payments.service;

import com.allstate.payments.data.PaymentRepository;
import com.allstate.payments.domain.Payment;
import com.allstate.payments.exceptions.PaymentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

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
}
